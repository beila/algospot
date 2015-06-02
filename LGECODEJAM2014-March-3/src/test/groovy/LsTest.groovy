/**
 * Created by hojin.ghim on 2015-06-02.
 */
class LsTest extends GroovyTestCase {
    Ls ls = new Ls();

    void testEmpty() {
        String[] words = ["a"]
        assert "a" == ls.run(1, words)
    }

    void testPadding() {
        String[] words = ["a"]
        assert "a" + ("_" * 9) == ls.run(10, words)
    }

    void testFirstExample() {
        String[] words = ["abc", "def", "ghi", "jkl"]
        assert "abc__def__ghi__jkl__" == ls.run(20, words)
    }

    void testSecondExample() {
        String[] words = ["abc", "def", "ghi", "jkl"]
        assert """abc__def__
                  ghi__jkl__ """.stripMargin() == ls.run(10, words)
    }
}
