public class PaperFolding {

    static void PrintPaperFolding(int n, boolean down)
    {
        if(n < 1)
            return;
        PrintPaperFolding(n - 1, true);
        System.out.println(down ? "D ":"U ");
        PrintPaperFolding(n - 1, false);
    }

    public static void main(String args[])
    {
        PrintPaperFolding(3, true);
    }
}
