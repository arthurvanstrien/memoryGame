import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Arthur on 5-6-2017.
 */
public class SendData {

    private DataOutputStream outputStream;

    public SendData(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void clicked(int index) {

        try {
            outputStream.writeUTF("CLICKED");
            outputStream.writeInt(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endTurn() {
        try {
            System.out.println("END TURN SEND");
            outputStream.writeUTF("ENDTURN");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void match(int card1, int card2) {
        try {
            outputStream.writeUTF("MATCH");
            outputStream.writeInt(card1);
            outputStream.writeInt(card2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
