package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.flashcard.Description;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Title;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Flashcard objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_TITLE = "Alice Pauline";
    public static final String DEFAULT_DESCRIPTION = "123, Jurong West Ave 6, #08-111";

    private Title title;
    private Description description;
    private Set<Tag> tags;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code flashcardToCopy}.
     */
    public FlashcardBuilder(Flashcard flashcardToCopy) {
        title =flashcardToCopy.getTitle();
        description = flashcardToCopy.getDescription();
        // tags = new HashSet<>(flashcardToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(title, description);
    }

}
