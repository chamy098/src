package com.src.application;

import com.src.core.exceptions.NotFoundException;
import com.src.datamodel.Actor;
import com.src.service.ActorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

@SpringBootTest
@RunWith(SpringRunner.class)
class ActorServiceTests {

    @Autowired
    private ActorService actorService;

    private Actor createTestActor() {
        Actor actor = new Actor();
        actor.setFirstName("Test actor");
        actor.setLastName("Test actor");
        actor.setDateOfBirth(Date.valueOf("1995-01-01"));
        return actor;
    }

    @Test
    public void testSaveActor() throws Exception {
        Actor actor = createTestActor();
        Actor savedActor = actorService.save(actor).get();

        // Check returned values
        assertThat(savedActor.getFirstName(), containsString(actor.getFirstName()));
        assertThat(savedActor.getLastName(), containsString(actor.getLastName()));
        assertThat(savedActor.getDateOfBirth(), equalTo(actor.getDateOfBirth()));
    }

    @Test
    public void testUpdateActor() throws  Exception {
        Actor actor = actorService.save(createTestActor()).get();
        actor.setFirstName("Updated first name");

        // Update the actor
        Actor updatedActor = actorService.update(actor).get();

        // Check returned values
        assertThat(updatedActor.getFirstName(), containsString(actor.getFirstName()));
        assertThat(updatedActor.getLastName(), containsString(actor.getLastName()));
        assertThat(updatedActor.getDateOfBirth(), equalTo(actor.getDateOfBirth()));
    }

    @Test
    public void testDelete() throws Exception {
        Actor actor = actorService.save(createTestActor()).get();
        Boolean isDeleted = actorService.delete(actor.getId()).get();

        // Check returned values
        assertThat(isDeleted, equalTo(true));
    }

    @Test
    public void testGetAllActors() throws Exception {
        actorService.save(createTestActor()).get();
        List<Actor> actors = actorService.getAll().get();

        // Check returned values
        assertFalse(actors.isEmpty());
    }

    @Test
    public void getActorsByPage() throws Exception {
        actorService.save(createTestActor()).get();
        Page<Actor> actors = actorService.getActorsByPage(10, 0).get();

        // Check returned values
        assertFalse(actors.isEmpty());
    }

    //Testing fails

    @Test
    public void testUpdateNonExistentActor() {
        Actor actor = createTestActor();
        actor.setId(100L);
        actor.setFirstName("Updated first name");

        try {
            actorService.update(actor).get();
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            // Check if the exception is the expected type
            assertThat(e, is(instanceOf(NotFoundException.class)));
        }
    }

    @Test
    public void testDeleteNonExistentActor() {
        try {
            actorService.delete(100L).get();
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            // Check if the exception is the expected type
            assertThat(e, is(instanceOf(NotFoundException.class)));
        }
    }

    @Test
    public void testMissingRequiredValue() {
        Actor actor = createTestActor();
        actor.setFirstName(null);

        try {
            actorService.save(actor).get();
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            // Check if the exception is the expected type
            assertThat(e, is(instanceOf(ExecutionException.class)));
        }
    }
}
