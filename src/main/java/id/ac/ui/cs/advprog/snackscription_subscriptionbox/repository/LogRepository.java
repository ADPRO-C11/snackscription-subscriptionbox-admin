package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.LogAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface LogRepository extends JpaRepository<LogAdmin, Integer> {
    CompletableFuture<List<LogAdmin>> findAllByOrderByDateDesc();
}
