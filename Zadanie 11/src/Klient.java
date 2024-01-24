import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;

class Klient implements NetConnection {

    BigInteger pass;

    @Override
    public void password(String password) {

        this.pass = new BigInteger(password);
    }

    @Override
    public void connect(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            reader.readLine();
            reader.readLine();
            reader.readLine();
            writer.println("Program");
            writer.flush();
            BigInteger acc = BigInteger.ZERO;
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null || line.equals("EOD")) {
                    reader.readLine();
                    break;
                }
                try {
                    BigInteger current = new BigInteger(line);
                    acc = acc.add(current);
                } catch (NumberFormatException e) {
                    continue;
                }
            }

            String resultLine = reader.readLine();
            BigInteger result = new BigInteger(resultLine.split(" ")[2]);
            BigInteger MyResult = acc.add(pass);
            if (!result.equals(MyResult)) {
                writer.println(ODPOWIEDZ_DLA_OSZUSTA);
            } else {
                writer.println(result);
            }
            writer.flush();
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

