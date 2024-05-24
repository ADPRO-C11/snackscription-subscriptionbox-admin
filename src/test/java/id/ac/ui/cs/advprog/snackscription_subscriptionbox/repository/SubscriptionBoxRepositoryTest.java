package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionBoxRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @Test
    void testSave() {
        SubscriptionBox subscriptionBox = new SubscriptionBox("Basic", "Monthly", 100, null);
        SubscriptionBox savedSubscriptionBox = subscriptionBoxRepository.save(subscriptionBox);
        assertEquals(subscriptionBox, savedSubscriptionBox);
        verify(entityManager, times(1)).persist(subscriptionBox);
    }

    @Test
    void testFindAll() {
        SubscriptionBox subscriptionBox1 = new SubscriptionBox("Basic", "Monthly", 100, null);
        SubscriptionBox subscriptionBox2 = new SubscriptionBox("Premium", "Monthly", 200, null);

        TypedQuery<SubscriptionBox> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT sb FROM SubscriptionBox sb", SubscriptionBox.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(subscriptionBox1, subscriptionBox2));

        List<SubscriptionBox> subscriptionBoxes = subscriptionBoxRepository.findAll();

        assertEquals(2, subscriptionBoxes.size());
        verify(entityManager, times(1)).createQuery("SELECT sb FROM SubscriptionBox sb", SubscriptionBox.class);
        verify(query, times(1)).getResultList();
    }

    @Test
    void testFindById() {
        SubscriptionBox subscriptionBox = new SubscriptionBox("Basic", "Monthly", 100, null);
        subscriptionBox.setId("1");

        when(entityManager.find(SubscriptionBox.class, "1")).thenReturn(subscriptionBox);

        Optional<SubscriptionBox> optionalSubscriptionBox = subscriptionBoxRepository.findById("1");

        assertEquals(Optional.of(subscriptionBox), optionalSubscriptionBox);
        verify(entityManager, times(1)).find(SubscriptionBox.class, "1");
    }

    @Test
    void testFindByIdSubscriptionNotFound() {
        when(entityManager.find(SubscriptionBox.class, "nonexistentId")).thenReturn(null);

        assertNull(subscriptionBoxRepository.findById("nonexistentId").orElse(null));

        verify(entityManager, times(1)).find(SubscriptionBox.class, "nonexistentId");
    }

    @Test
    void testUpdate() {
        SubscriptionBox subscriptionBox = new SubscriptionBox("Basic", "Monthly", 100, null);

        when(entityManager.merge(subscriptionBox)).thenReturn(subscriptionBox);

        SubscriptionBox updatedSubscriptionBox = subscriptionBoxRepository.update(subscriptionBox);

        assertEquals(subscriptionBox, updatedSubscriptionBox);
        verify(entityManager, times(1)).merge(subscriptionBox);
    }

    @Test
    void testDelete() {
        SubscriptionBox subscriptionBox = new SubscriptionBox("Basic", "Monthly", 100, null);
        subscriptionBox.setId("1");

        when(entityManager.find(SubscriptionBox.class, "1")).thenReturn(subscriptionBox);
        doNothing().when(entityManager).remove(subscriptionBox);

        subscriptionBoxRepository.delete("1");

        verify(entityManager, times(1)).find(SubscriptionBox.class, "1");
        verify(entityManager, times(1)).remove(subscriptionBox);
    }

    @Test
    void testDeleteSubscriptionNotFound() {
        when(entityManager.find(SubscriptionBox.class, "1")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> subscriptionBoxRepository.delete("1"));

        verify(entityManager, times(1)).find(SubscriptionBox.class, "1");
        verify(entityManager, never()).remove(any());
    }

    @Test
    void testFindByPriceLessThan() {
        SubscriptionBox subscriptionBox1 = new SubscriptionBox("Basic", "Monthly", 100, null);
        SubscriptionBox subscriptionBox2 = new SubscriptionBox("Premium", "Monthly", 200, null);

        TypedQuery<SubscriptionBox> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT sb FROM SubscriptionBox sb WHERE sb.price < :price", SubscriptionBox.class)).thenReturn(query);
        when(query.setParameter("price", 150)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(subscriptionBox1));

        List<SubscriptionBox> result = subscriptionBoxRepository.findByPriceLessThan(150);

        assertEquals(1, result.size());
        assertEquals(subscriptionBox1, result.get(0));
        verify(entityManager, times(1)).createQuery("SELECT sb FROM SubscriptionBox sb WHERE sb.price < :price", SubscriptionBox.class);
        verify(query, times(1)).setParameter("price", 150);
        verify(query, times(1)).getResultList();
    }

    @Test
    void testFindByPriceGreaterThan() {
        SubscriptionBox subscriptionBox1 = new SubscriptionBox("Basic", "Monthly", 100, null);
        SubscriptionBox subscriptionBox2 = new SubscriptionBox("Premium", "Monthly", 200, null);

        TypedQuery<SubscriptionBox> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT sb FROM SubscriptionBox sb WHERE sb.price > :price", SubscriptionBox.class)).thenReturn(query);
        when(query.setParameter("price", 150)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(subscriptionBox2));

        List<SubscriptionBox> result = subscriptionBoxRepository.findByPriceGreaterThan(150);

        assertEquals(1, result.size());
        assertEquals(subscriptionBox2, result.get(0));
        verify(entityManager, times(1)).createQuery("SELECT sb FROM SubscriptionBox sb WHERE sb.price > :price", SubscriptionBox.class);
        verify(query, times(1)).setParameter("price", 150);
        verify(query, times(1)).getResultList();
    }

    @Test
    void testFindByPriceEquals() {
        SubscriptionBox subscriptionBox1 = new SubscriptionBox("Basic", "Monthly", 100, null);
        SubscriptionBox subscriptionBox2 = new SubscriptionBox("Premium", "Monthly", 200, null);

        TypedQuery<SubscriptionBox> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT sb FROM SubscriptionBox sb WHERE sb.price = :price", SubscriptionBox.class)).thenReturn(query);
        when(query.setParameter("price", 100)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(subscriptionBox1));

        List<SubscriptionBox> result = subscriptionBoxRepository.findByPriceEquals(100);

        assertEquals(1, result.size());
        assertEquals(subscriptionBox1, result.get(0));
        verify(entityManager, times(1)).createQuery("SELECT sb FROM SubscriptionBox sb WHERE sb.price = :price", SubscriptionBox.class);
        verify(query, times(1)).setParameter("price", 100);
        verify(query, times(1)).getResultList();
    }

    @Test
    void testFindByName() {
        SubscriptionBox subscriptionBox1 = new SubscriptionBox("Basic Box", "Monthly", 100, null);
        SubscriptionBox subscriptionBox2 = new SubscriptionBox("Premium Box", "Monthly", 200, null);

        TypedQuery<SubscriptionBox> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT sb FROM SubscriptionBox sb WHERE LOWER(sb.name) LIKE LOWER(:name)", SubscriptionBox.class)).thenReturn(query);
        when(query.setParameter("name", "%box%")).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(subscriptionBox1, subscriptionBox2));

        Optional<List<SubscriptionBox>> result = subscriptionBoxRepository.findByName("box");

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        verify(entityManager, times(1)).createQuery("SELECT sb FROM SubscriptionBox sb WHERE LOWER(sb.name) LIKE LOWER(:name)", SubscriptionBox.class);
        verify(query, times(1)).setParameter("name", "%box%");
        verify(query, times(1)).getResultList();
    }

    @Test
    void testFindDistinctNames() {
        TypedQuery<String> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT DISTINCT sb.name FROM SubscriptionBox sb", String.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList("Basic Box", "Premium Box"));

        Optional<List<String>> result = subscriptionBoxRepository.findDistinctNames();

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertTrue(result.get().contains("Basic Box"));
        assertTrue(result.get().contains("Premium Box"));
        verify(entityManager, times(1)).createQuery("SELECT DISTINCT sb.name FROM SubscriptionBox sb", String.class);
        verify(query, times(1)).getResultList();
    }
}
