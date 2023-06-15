import javax.swing.JOptionPane;
public class GetData {
    public static double getDouble(String s) {
        return Double.parseDouble(getWord(s));
    }
    public static int getInt(String s) {
        return Integer.parseInt(getWord(s));
    }
    public static String getWord(String s) {
        return JOptionPane.showInputDialog(s);
    }
    public static String getString(String s) {
        return JOptionPane.showInputDialog(s);
    }
}

