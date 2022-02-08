package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class LongestCommonSubsequenceTest {

    private LongestCommonSubsequence matrix;

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenStringsAreNull() {
        matrix = new LongestCommonSubsequence(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenFirstStringIsNull() {
        matrix = new LongestCommonSubsequence(null, "anything");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenSecondStringIsNull() {
        matrix = new LongestCommonSubsequence("anything", null);
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForIdenticalOneLetterWords() {
        LCSClassTestingMethod("c", "c", "c");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForDifferentOneLetterWords() {
        LCSClassTestingMethod("c", "g", "");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForIdenticalTwoLetterWords() {
        LCSClassTestingMethod("ch", "ch", "ch");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForDifferentTwoLetterWords() {
        LCSClassTestingMethod("ce", "fg", "");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForIdenticalWords() {
        LCSClassTestingMethod("checking", "checking", "checking");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithoutCommonSigns() {
        LCSClassTestingMethod("pies", "kot", "");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithTwoSameLengthLCS() {
        LCSClassTestingMethod("politechnika", "chnikapolite", "chnika");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithTwoDifferentLengthLCS() {
        LCSClassTestingMethod("politetchnika", "chnikapolitet", "politet");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForOneEmptyWord() {
        LCSClassTestingMethod("", "not_empty", "");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForOneEmptyWordOnSecondPosition() {
        LCSClassTestingMethod("not_empty", "", "");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForEmptyWords() {
        LCSClassTestingMethod("", "", "");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForExampleWords() {
        LCSClassTestingMethod("prezent", "rezerwat", "rezet");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithSpaceCharacter() {
        LCSClassTestingMethod("not to be", "to be not", "to be");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithSpaceCharacter2() {
        LCSClassTestingMethod("new\nline", "new\n\rline", "new\nline");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithNewLineCharacter3() {
        LCSClassTestingMethod("new\n"+'\r'+"\nline", "new\n"+'\r'+"\nline", "new\n"+'\r'+"\nline");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithNewLineCharacter() {
        LCSClassTestingMethod("new" + '\n' + "line" + '\n', '\n' + "line" + '\n' + "new", '\n' + "line" + '\n');
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithTabCharacter() {
        LCSClassTestingMethod("new" + '\t' + "line" + '\t', '\t' + "line" + '\t' + "new", '\t' + "line" + '\t');
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithRCharacter() {
        LCSClassTestingMethod("/|\\" + '\r' + ":;'." + '\r', '\r' + ":;'." + '\r' + "/|\\", '\r' + ":;'." + '\r');
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForWordsWithDigits() {
        LCSClassTestingMethod("123456789", "13579", "13579");
    }

    @Test
    public void shouldCorrectlyFindLCSAndDisplayTableForIdenticalWordsWith20SameCharacters() {
        LCSClassTestingMethod("aaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaa");
    }

    private void LCSClassTestingMethod(String topStr, String leftStr, String expectedStr) {
        matrix = new LongestCommonSubsequence(topStr, leftStr);
        assertEquals(expectedStr, matrix.findLCS());
        matrix.display();
    }

}