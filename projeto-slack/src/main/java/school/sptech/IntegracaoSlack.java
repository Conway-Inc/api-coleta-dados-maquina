package school.sptech;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import java.io.IOException;

public class IntegracaoSlack {

    public static void enviarMensagem(String mensagem) {
        String token = "xoxb-6256319742354-6282917773120-0kWVR5t001i2mFGmY4YT8QL6";
        String channelId = "D067905AA07";

        try {
            Slack slack = Slack.getInstance();
            ChatPostMessageRequest messageRequest = ChatPostMessageRequest.builder()
                    .token(token)
                    .channel(channelId)
                    .text(mensagem)
                    .build();

            ChatPostMessageResponse response = slack.methods().chatPostMessage(messageRequest);

            if (response.isOk()) {
                System.out.println("Mensagem enviada com sucesso!");
            } else {
                System.err.println("Erro ao enviar a mensagem: " + response.getError());
            }
        } catch (IOException | SlackApiException e) {
            e.printStackTrace();
        }
    }
}
