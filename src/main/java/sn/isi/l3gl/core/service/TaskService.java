package sn.isi.l3gl.core.service;

import org.springframework.stereotype.Service;
import sn.isi.l3gl.core.entity.Task;
import sn.isi.l3gl.core.enums.TaskStatus;
import sn.isi.l3gl.core.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // VERSION 0.0.1 — createTask()
    public Task createTask(String title, String description) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(TaskStatus.TODO);
        return taskRepository.save(task);
    }
    // VERSION 0.1.0 — listTasks()
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }
    // VERSION 0.2.0
    public Task updateStatus(Long id, TaskStatus newStatus) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche introuvable avec l'id : " + id));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }
    // VERSION 0.3.0
    public long countCompletedTasks() {
        return taskRepository.countByStatus(TaskStatus.DONE);
    }
}