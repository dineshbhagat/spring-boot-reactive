package com.example.entity;

import com.example.annotations.CascadeSave;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

@Document(collection = "article")
@Data
public class Article {

    /**
     *
     * 1. A property or field annotated with @Id (org.springframework.data.annotation.Id) maps to the _id field.
     *
     * 2. A property or field without an annotation but named id maps to the _id field.
     */
    @Id
    private String id;

    @Field("article_text")
    private String articleText;

    /**
     * Using DBRefs <url>https://www.baeldung.com/cascading-with-dbref-and-lifecycle-events-in-spring-data-mongodb</url>
     * The mapping framework doesn't have to store child objects embedded within the document.
     * You can also store them separately and use a DBRef to refer to that document.
     * When the object is loaded from MongoDB, those references will be eagerly resolved and
     * you will get back a mapped object that looks the same as if it had been stored embedded
     * within your master document.
     *
     * Here's an example of using a DBRef to refer to a specific document that exists independently of
     * the object in which it is referenced
     *
     * There's no need to use something like @OneToMany because the mapping framework sees that you're
     * wanting a one-to-many relationship because there is a List of objects.
     * When the object is stored in MongoDB, there will be a list of DBRefs rather than the Account objects themselves.
     *
     * The mapping framework does not handle cascading saves.
     * If you change an Comment object that is referenced by a Article object, you must save the Comment object separately.
     * Calling save on the Article object will not automatically save the Comment objects in the property comments.
     * similarly for tags too.
     *
     * So How do we achieve this?
     * This is precisely where life cycle events come in handy.
     * we’ll need to explicitly trigger the save on the child if we want to save it as well.
     *
     * Lifecycle Events:
     * 1. onBeforeConvert
     * 2. onBeforeSave
     * 3. onAfterSave
     * 4. onAfterLoad
     * 5. onAfterConvert
     *
     * To intercept one of the events, we need to register a subclass of AbstractMappingEventListener
     * and override one of the methods here.
     * When the event is dispatched, our listener will be called and domain object passed in.
     */
    @DBRef
    @CascadeSave
    @Field("comments")
    private List<Comment> comments = Lists.newArrayList();

    @DBRef
    @CascadeSave
    @Field("tags")
    private Set<Tag> tags = Sets.newHashSet();
}
