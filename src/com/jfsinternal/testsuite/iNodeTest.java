import com.jfsinternal.BlockIO;

/**
 * Created by nicholas on 22/11/14.
 */
public class iNodeTest {

    public static void main(String[] args) {
        BlockIO disk = new BlockIO();

        byte[] buffer = new byte[128];

        disk.getBlock(11, buffer);

        byte status = buffer[0];


        short location = (byte) (((buffer[1] & 0x00ff) << 8)
                + (buffer[2] & 0x00ff));


        String name = new String(buffer, 3, 6);


        short Type = (short) (((buffer[9] & 0x00ff) << 8)
                + (buffer[10] & 0x00ff));

        short openCount = (short) (((buffer[11] & 0x00ff) << 8)
                + (buffer[12] & 0x00ff));

        short iNumber = (short) (((buffer[13] & 0x00ff) << 8)
                + (buffer[14] & 0x00ff));

        short size = (short) (((buffer[15] & 0x00ff) << 8)
                + (buffer[16] & 0x00ff));

        String cDate = new String(buffer, 17, 28);

        System.out.println(status);
        System.out.println(location);
        System.out.println(name);
        System.out.println(Type);
        System.out.println(openCount);
        System.out.println(iNumber);
        System.out.println(size);
        System.out.println(cDate);

        for (int i = 0; i < buffer.length; i++) {
            System.out.print(buffer[i]);
        }


    }
}
