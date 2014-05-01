import java.io.InputStream;
import java.io.PrintStream;
import java.util.BitSet;
import java.util.Scanner;

/**
 * http://algospot.com/judge/problem/read/BOARDCOVER
 * Created by hojin.ghim on 3/15/14.
 */
public class Main {
    static boolean debug = true;

    static int countLayoutCases(Board board) {
        if (board.allCovered()) return 0;
        if (board.getOpenCount() % 3 != 0) return 0;
        return countLayoutCasesOnSubBoard(board, 0);
    }

    static int countLayoutCasesOnSubBoard(Board board, int startIndex) {
        int i = board.getNextOpenIndex(startIndex);
        if (i < 0) return board.allCovered()? 1: 0;

        Point point = Point.get(i);
        int sum = 0;
        for (Cover cover: Cover.values()) {
            if (!board.coverableAt(point, cover)) continue;
            board.coverAt(point, cover);
            sum += countLayoutCasesOnSubBoard(board, i + 1);
            board.uncoverAt(point, cover);
        }

        if (board.openAt(point)) {
            sum += countLayoutCasesOnSubBoard(board, i + 1);
        }

        return sum;
    }

    static enum Point {
        // 0 and 21 are boundaries
        P0000(0,0), P0001(0,1), P0002(0,2), P0003(0,3), P0004(0,4), P0005(0,5), P0006(0,6), P0007(0,7), P0008(0,8), P0009(0,9), P0010(0,10), P0011(0,11), P0012(0,12), P0013(0,13), P0014(0,14), P0015(0,15), P0016(0,16), P0017(0,17), P0018(0,18), P0019(0,19), P0020(0,20), P0021(0,21),
        P0100(1,0), P0101(1,1), P0102(1,2), P0103(1,3), P0104(1,4), P0105(1,5), P0106(1,6), P0107(1,7), P0108(1,8), P0109(1,9), P0110(1,10), P0111(1,11), P0112(1,12), P0113(1,13), P0114(1,14), P0115(1,15), P0116(1,16), P0117(1,17), P0118(1,18), P0119(1,19), P0120(1,20), P0121(1,21),
        P0200(2,0), P0201(2,1), P0202(2,2), P0203(2,3), P0204(2,4), P0205(2,5), P0206(2,6), P0207(2,7), P0208(2,8), P0209(2,9), P0210(2,10), P0211(2,11), P0212(2,12), P0213(2,13), P0214(2,14), P0215(2,15), P0216(2,16), P0217(2,17), P0218(2,18), P0219(2,19), P0220(2,20), P0221(2,21),
        P0300(3,0), P0301(3,1), P0302(3,2), P0303(3,3), P0304(3,4), P0305(3,5), P0306(3,6), P0307(3,7), P0308(3,8), P0309(3,9), P0310(3,10), P0311(3,11), P0312(3,12), P0313(3,13), P0314(3,14), P0315(3,15), P0316(3,16), P0317(3,17), P0318(3,18), P0319(3,19), P0320(3,20), P0321(3,21),
        P0400(4,0), P0401(4,1), P0402(4,2), P0403(4,3), P0404(4,4), P0405(4,5), P0406(4,6), P0407(4,7), P0408(4,8), P0409(4,9), P0410(4,10), P0411(4,11), P0412(4,12), P0413(4,13), P0414(4,14), P0415(4,15), P0416(4,16), P0417(4,17), P0418(4,18), P0419(4,19), P0420(4,20), P0421(4,21),
        P0500(5,0), P0501(5,1), P0502(5,2), P0503(5,3), P0504(5,4), P0505(5,5), P0506(5,6), P0507(5,7), P0508(5,8), P0509(5,9), P0510(5,10), P0511(5,11), P0512(5,12), P0513(5,13), P0514(5,14), P0515(5,15), P0516(5,16), P0517(5,17), P0518(5,18), P0519(5,19), P0520(5,20), P0521(5,21),
        P0600(6,0), P0601(6,1), P0602(6,2), P0603(6,3), P0604(6,4), P0605(6,5), P0606(6,6), P0607(6,7), P0608(6,8), P0609(6,9), P0610(6,10), P0611(6,11), P0612(6,12), P0613(6,13), P0614(6,14), P0615(6,15), P0616(6,16), P0617(6,17), P0618(6,18), P0619(6,19), P0620(6,20), P0621(6,21),
        P0700(7,0), P0701(7,1), P0702(7,2), P0703(7,3), P0704(7,4), P0705(7,5), P0706(7,6), P0707(7,7), P0708(7,8), P0709(7,9), P0710(7,10), P0711(7,11), P0712(7,12), P0713(7,13), P0714(7,14), P0715(7,15), P0716(7,16), P0717(7,17), P0718(7,18), P0719(7,19), P0720(7,20), P0721(7,21),
        P0800(8,0), P0801(8,1), P0802(8,2), P0803(8,3), P0804(8,4), P0805(8,5), P0806(8,6), P0807(8,7), P0808(8,8), P0809(8,9), P0810(8,10), P0811(8,11), P0812(8,12), P0813(8,13), P0814(8,14), P0815(8,15), P0816(8,16), P0817(8,17), P0818(8,18), P0819(8,19), P0820(8,20), P0821(8,21),
        P0900(9,0), P0901(9,1), P0902(9,2), P0903(9,3), P0904(9,4), P0905(9,5), P0906(9,6), P0907(9,7), P0908(9,8), P0909(9,9), P0910(9,10), P0911(9,11), P0912(9,12), P0913(9,13), P0914(9,14), P0915(9,15), P0916(9,16), P0917(9,17), P0918(9,18), P0919(9,19), P0920(9,20), P0921(9,21),
        P1000(10,0), P1001(10,1), P1002(10,2), P1003(10,3), P1004(10,4), P1005(10,5), P1006(10,6), P1007(10,7), P1008(10,8), P1009(10,9), P1010(10,10), P1011(10,11), P1012(10,12), P1013(10,13), P1014(10,14), P1015(10,15), P1016(10,16), P1017(10,17), P1018(10,18), P1019(10,19), P1020(10,20), P1021(10,21),
        P1100(11,0), P1101(11,1), P1102(11,2), P1103(11,3), P1104(11,4), P1105(11,5), P1106(11,6), P1107(11,7), P1108(11,8), P1109(11,9), P1110(11,10), P1111(11,11), P1112(11,12), P1113(11,13), P1114(11,14), P1115(11,15), P1116(11,16), P1117(11,17), P1118(11,18), P1119(11,19), P1120(11,20), P1121(11,21),
        P1200(12,0), P1201(12,1), P1202(12,2), P1203(12,3), P1204(12,4), P1205(12,5), P1206(12,6), P1207(12,7), P1208(12,8), P1209(12,9), P1210(12,10), P1211(12,11), P1212(12,12), P1213(12,13), P1214(12,14), P1215(12,15), P1216(12,16), P1217(12,17), P1218(12,18), P1219(12,19), P1220(12,20), P1221(12,21),
        P1300(13,0), P1301(13,1), P1302(13,2), P1303(13,3), P1304(13,4), P1305(13,5), P1306(13,6), P1307(13,7), P1308(13,8), P1309(13,9), P1310(13,10), P1311(13,11), P1312(13,12), P1313(13,13), P1314(13,14), P1315(13,15), P1316(13,16), P1317(13,17), P1318(13,18), P1319(13,19), P1320(13,20), P1321(13,21),
        P1400(14,0), P1401(14,1), P1402(14,2), P1403(14,3), P1404(14,4), P1405(14,5), P1406(14,6), P1407(14,7), P1408(14,8), P1409(14,9), P1410(14,10), P1411(14,11), P1412(14,12), P1413(14,13), P1414(14,14), P1415(14,15), P1416(14,16), P1417(14,17), P1418(14,18), P1419(14,19), P1420(14,20), P1421(14,21),
        P1500(15,0), P1501(15,1), P1502(15,2), P1503(15,3), P1504(15,4), P1505(15,5), P1506(15,6), P1507(15,7), P1508(15,8), P1509(15,9), P1510(15,10), P1511(15,11), P1512(15,12), P1513(15,13), P1514(15,14), P1515(15,15), P1516(15,16), P1517(15,17), P1518(15,18), P1519(15,19), P1520(15,20), P1521(15,21),
        P1600(16,0), P1601(16,1), P1602(16,2), P1603(16,3), P1604(16,4), P1605(16,5), P1606(16,6), P1607(16,7), P1608(16,8), P1609(16,9), P1610(16,10), P1611(16,11), P1612(16,12), P1613(16,13), P1614(16,14), P1615(16,15), P1616(16,16), P1617(16,17), P1618(16,18), P1619(16,19), P1620(16,20), P1621(16,21),
        P1700(17,0), P1701(17,1), P1702(17,2), P1703(17,3), P1704(17,4), P1705(17,5), P1706(17,6), P1707(17,7), P1708(17,8), P1709(17,9), P1710(17,10), P1711(17,11), P1712(17,12), P1713(17,13), P1714(17,14), P1715(17,15), P1716(17,16), P1717(17,17), P1718(17,18), P1719(17,19), P1720(17,20), P1721(17,21),
        P1800(18,0), P1801(18,1), P1802(18,2), P1803(18,3), P1804(18,4), P1805(18,5), P1806(18,6), P1807(18,7), P1808(18,8), P1809(18,9), P1810(18,10), P1811(18,11), P1812(18,12), P1813(18,13), P1814(18,14), P1815(18,15), P1816(18,16), P1817(18,17), P1818(18,18), P1819(18,19), P1820(18,20), P1821(18,21),
        P1900(19,0), P1901(19,1), P1902(19,2), P1903(19,3), P1904(19,4), P1905(19,5), P1906(19,6), P1907(19,7), P1908(19,8), P1909(19,9), P1910(19,10), P1911(19,11), P1912(19,12), P1913(19,13), P1914(19,14), P1915(19,15), P1916(19,16), P1917(19,17), P1918(19,18), P1919(19,19), P1920(19,20), P1921(19,21),
        P2000(20,0), P2001(20,1), P2002(20,2), P2003(20,3), P2004(20,4), P2005(20,5), P2006(20,6), P2007(20,7), P2008(20,8), P2009(20,9), P2010(20,10), P2011(20,11), P2012(20,12), P2013(20,13), P2014(20,14), P2015(20,15), P2016(20,16), P2017(20,17), P2018(20,18), P2019(20,19), P2020(20,20), P2021(20,21),
        P2100(21,0), P2101(21,1), P2102(21,2), P2103(21,3), P2104(21,4), P2105(21,5), P2106(21,6), P2107(21,7), P2108(21,8), P2109(21,9), P2110(21,10), P2111(21,11), P2112(21,12), P2113(21,13), P2114(21,14), P2115(21,15), P2116(21,16), P2117(21,17), P2118(21,18), P2119(21,19), P2120(21,20), P2121(21,21),
        ;
        static private int SIDE_LENGTH_BOUND = 22;
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Point get(int x, int y) {
            return Point.values()[y * SIDE_LENGTH_BOUND + x];
        }

        static Point get(int i) {
            return Point.values()[i];
        }
    }

    static class Board {
        // false if already covered
        // true if available
        // BitSet initializes with all false
        BitSet covered = new BitSet(Point.values().length);

        boolean coverableWith(Point p) {
            return covered.get(p.ordinal());
        }

        boolean openAt(Point p) {
            return !coverableWith(p);
        }

        void coverWith(Point p) {
            covered.clear(p.ordinal());
        }

        void uncoverWith(Point p) {
            covered.set(p.ordinal());
        }

        boolean coverableAt(Point p, Cover cover) {
            return cover.coverable(this, p);
        }

        void coverAt(Point p, Cover c) {
            c.cover(this, p);
        }

        void uncoverAt(Point p, Cover c) {
            c.uncover(this, p);
        }

        boolean allCovered() {
            return covered.isEmpty();
        }

        int getOpenCount() {
            return covered.cardinality();
        }

        int getNextOpenIndex(int index) {
            return covered.nextSetBit(index);
        }

        void print(PrintStream ps) {
            if (!Main.debug) return;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Point.SIDE_LENGTH_BOUND*Point.SIDE_LENGTH_BOUND; i++) {
                sb.append('#');
            }
            for (int i = covered.nextSetBit(0); i >= 0; i = covered.nextSetBit(i+1)) {
                sb.setCharAt(i, '.');
            }
            for (int i = sb.length(); i >= 0; i -= Point.SIDE_LENGTH_BOUND) {
                sb.insert(i, String.format("%n"));
            }
            ps.println(sb);
        }

    }

    static enum RelativePoint {
        A(-1,-1), B(0,-1), C(1,-1),
        E(-1,0),  F(0,0),  G(1,0),
        H(-1,1),  I(0,1),  J(1,1);

        private final int x;
        private final int y;
        RelativePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point getPoint(Point p) {
            return Point.get(p.x+x, p.y+y);
        }
    }

    static enum Cover {
        A(RelativePoint.A, RelativePoint.B, RelativePoint.F),
        B(RelativePoint.C, RelativePoint.G, RelativePoint.F),
        C(RelativePoint.J, RelativePoint.I, RelativePoint.F),
        D(RelativePoint.H, RelativePoint.E, RelativePoint.F),
        E(RelativePoint.C, RelativePoint.B, RelativePoint.F),
        F(RelativePoint.A, RelativePoint.E, RelativePoint.F),
        G(RelativePoint.H, RelativePoint.I, RelativePoint.F),
        H(RelativePoint.J, RelativePoint.G, RelativePoint.F);

        RelativePoint[] relativePoints;
        Cover(RelativePoint... points) {
            relativePoints = points;
        }

        void cover(Board b, Point p) {
            for (RelativePoint rp: relativePoints) {
                b.coverWith(rp.getPoint(p));
            }
        }

        void uncover(Board b, Point p) {
            for (RelativePoint rp: relativePoints) {
                b.uncoverWith(rp.getPoint(p));
            }
        }

        public boolean coverable(Board b, Point p) {
            for (RelativePoint rp: relativePoints) {
                if (!b.coverableWith(rp.getPoint(p))) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Main.debug = false;
        main(System.in, System.out);
    }

    public static void main(InputStream inputStream, PrintStream printStream) {
        Scanner scanner = new Scanner(inputStream);
        final int numCases = scanner.nextInt();
        for (int i = 0; i < numCases; i++) {
            Board board = new Board();

            final int height = scanner.nextInt();
            final int width = scanner.nextInt();
            scanner.nextLine();
            for (int j = 0; j < height; j++) {
                char[] line = scanner.nextLine().toCharArray();
                for (int k = 0; k < width; k++) {
                    if ('.' == line[k]) board.uncoverWith(Point.get(k + 1, j + 1));
                }
            }
            board.print(System.err);
            printStream.println(Main.countLayoutCases(board));
        }
    }
}
