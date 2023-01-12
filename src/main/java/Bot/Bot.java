package Bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) throws LoginException {

        JDABuilder jda = JDABuilder.createDefault(args[0])
                .setActivity(Activity.watching("KAIJI"));
        jda.build();
    }
}