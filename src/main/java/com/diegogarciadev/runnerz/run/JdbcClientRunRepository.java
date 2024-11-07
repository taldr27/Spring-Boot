package com.diegogarciadev.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientRunRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientRunRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO run (id, title, started_on, completed_on, miles, location) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
                .update();
    }

    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Run not found" + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM run WHERE id = :id")
                .param("id", id)
                .update();
        Assert.state(updated == 1, "Run not found" + id);
    }

    public int count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    //    private List<Run> runs = new ArrayList<>();

//    public List<Run> findAll() {
//        return runs;
//    }
//
//    public Optional<Run> findById(Integer id) {
//        return runs.stream()
//                .filter(run -> run.id().equals(id))
//                .findFirst();
//    }
//
//    public void create(Run run) {
//        runs.add(run);
//    }
//
//    public void update(Run run, Integer id) {
//        Optional<Run> existingRun = findById(id);
//
//        if (existingRun.isPresent()) {
//            runs.remove(existingRun.get());
//            runs.add(run);
//        }
//    }
//
//    public void delete(Integer id) {
//        Optional<Run> existingRun = findById(id);
//
//        if (existingRun.isPresent()) {
//            runs.remove(existingRun.get());
//        }
//    }
//
//    @PostConstruct
//    private void init() {
//        runs.add(new Run(1, "First run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 5, Location.OUTDOOR));
//        runs.add(new Run(2, "Second run", LocalDateTime.now(), LocalDateTime.now().plus(40, ChronoUnit.MINUTES), 5, Location.OUTDOOR));
//        runs.add(new Run(3, "Third run", LocalDateTime.now(), LocalDateTime.now().plus(50, ChronoUnit.MINUTES), 5, Location.OUTDOOR));
//    }
}
