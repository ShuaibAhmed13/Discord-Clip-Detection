package Bot;

import Response.Response;
import Response.Result;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class Bot extends ListenerAdapter {


    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault(args[0])
                .setActivity(Activity.watching("KAIJI"))
                .addEventListeners(new Bot())
                .build();

        jda.updateCommands().addCommands(
                Commands.slash("search-by-url", "Get info of any anime by providing a link to a screenshot")
                        .addOption(OptionType.STRING, "url", "url to the image of the anime you want to find", true),
                Commands.slash("search-by-pic", "Get info of any anime by providing a still from it")
                        .addOption(OptionType.ATTACHMENT, "screenshot", "a still of the anime you want to find", true)
        ).queue();
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("Bot is ready!");
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "search-by-url":
                String content = event.getOption("url").getAsString();
                event.deferReply().queue();
                try {
                    Response response = Http.Connection.getInfoByUrlAnilistApache(content);
                    Result result = response.getResult(0);

                    event.getHook().sendMessage(response.getError())
//                    event.reply(response.getError()).setEphemeral(false)
//                            .addEmbeds(new EmbedBuilder().setImage(result.getImage()).build())
                            .addEmbeds(new EmbedBuilder()
                                    .setTitle(result.getAnilist().getTitle().getEnglish(), result.getVideo())
                                    .setImage(result.getImage())
//                                    .setAuthor(event.getInteraction().getUser().getName(),
//                                            event.getInteraction().getUser().getEffectiveAvatarUrl(),
//                                            event.getInteraction().getUser().getEffectiveAvatarUrl())
//                                    .setThumbnail(result.getImage())
//                                    .addBlankField(true)
                                    .setFooter("Similarities under 90% indicate very high likeliness of inaccurate results")
                                    .setColor(new Color(84, 19, 168))
                                    .addField("Name: ", result.getAnilist().getTitle().getEnglish(), false)
                                    .addField("Episode: ", result.getEpisode(), false)
                                    .addField("Similarity: ", result.getSimilarity() + "%", false)
                                    .addField("Time in episode: ", result.getFrom() + " - " + result.getTo(), false)
                                    .build())
                            .queue();
//                    MessageChannel messageChannel = event.getMessageChannel();
//                    messageChannel.sendMessage(response.getResult(0).getVideo()).queue();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search-by-pic":
                event.deferReply().queue();
                Message.Attachment file = event.getOption("screenshot").getAsAttachment();

//                System.out.println(file.downloadToFile());
                try {
//                    File file1 = file.downloadToFile().get(10, TimeUnit.SECONDS);
//
//                    Response response = Http.Connection.getInfoByPicApache(file1);
                    Response response = Http.Connection.getInfoByUrlAnilistApache(file.getUrl());

                    Result result = response.getResult(0);

                    event.getHook().sendMessage(response.getError())
//                    event.reply(response.getError()).setEphemeral(false)
//                            .addEmbeds(new EmbedBuilder().setImage(result.getImage()).build())
                            .addEmbeds(new EmbedBuilder()
                                    .setTitle(result.getAnilist().getTitle().getEnglish(), result.getVideo())
                                    .setImage(result.getImage())
//                                    .setAuthor(event.getInteraction().getUser().getName(),
//                                            event.getInteraction().getUser().getEffectiveAvatarUrl(),
//                                            event.getInteraction().getUser().getEffectiveAvatarUrl())
//                                    .setThumbnail(result.getImage())
//                                    .addBlankField(true)
                                    .setFooter("Similarities under 90% indicate very high likeliness of inaccurate results")
                                    .setColor(new Color(84, 19, 168))
                                    .addField("Name: ", result.getAnilist().getTitle().getEnglish(), false)
                                    .addField("Episode: ", result.getEpisode(), false)
                                    .addField("Similarity: ", result.getSimilarity() + "%", false)
                                    .addField("Time in episode: ", result.getFrom() + " - " + result.getTo(), false)
                                    .build())
                            .queue();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }

    }
}
