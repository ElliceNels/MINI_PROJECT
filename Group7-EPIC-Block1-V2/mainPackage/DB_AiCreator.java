package mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class DB_AiCreator {
    //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

    // using chatgpt api, create questions and answers for the database

    // create a mainPackage.DB_Handler.card object for each question and answer
    static String CurlCommand = """
     curl https://api.openai.com/v1/chat/completions \
     -H "Authorization: Bearer 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" \
     -H "Content-Type: application/json" \
     -d '{"model": "gpt-3.5-turbo", "messages": [{"role": "user", "content": "What is the OpenAI mission?"}]}'
     """;

    // curl in java
    public static void main(String[] Args) throws IOException {
        Process process = Runtime.getRuntime().exec(CurlCommand);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();

    }
    // https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java

}
