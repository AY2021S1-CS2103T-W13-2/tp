package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashcard;
import static seedu.address.testutil.TypicalFlashcards.ALICE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.exceptions.DuplicateFlashcardException;
import seedu.address.testutil.FlashcardBuilder;

public class BagelTest {

    private final Bagel bagel = new Bagel();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bagel.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bagel.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyBagel_replacesData() {
        Bagel newData = getTypicalBagel();
        bagel.resetData(newData);
        assertEquals(newData, bagel);
    }

    @Test
    public void resetData_withDuplicateFlashcards_throwsDuplicatePersonException() {
        // Two flashcards with the same information fields
        Flashcard editedAlice = new FlashcardBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Flashcard> newFlashcards = Arrays.asList(ALICE, editedAlice);
        BagelStub newData = new BagelStub(newFlashcards);

        assertThrows(DuplicateFlashcardException.class, () -> bagel.resetData(newData));
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bagel.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInBagel_returnsFalse() {
        assertFalse(bagel.hasFlashcard(ALICE));
    }

    @Test
    public void hasFlashcard_flashcardInBagel_returnsTrue() {
        bagel.addFlashcard(ALICE);
        assertTrue(bagel.hasFlashcard(ALICE));
    }

    @Test
    public void hasFlashcard_flashcardWithSameInformationFieldsInBagel_returnsTrue() {
        bagel.addFlashcard(ALICE);
        Flashcard editedAlice = new FlashcardBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(bagel.hasFlashcard(editedAlice));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bagel.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyBagel whose flashcards list can violate interface constraints.
     */
    private static class BagelStub implements ReadOnlyBagel {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        BagelStub(Collection<Flashcard> flashcards) {
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }
    }

}
