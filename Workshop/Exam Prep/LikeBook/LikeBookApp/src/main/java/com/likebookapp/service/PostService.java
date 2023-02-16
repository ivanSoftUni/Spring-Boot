package com.likebookapp.service;

import com.likebookapp.model.dtos.AddPostDto;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PostService {


    private LoggedUser loggedUser;
    private PostRepository postRepository;
    private MoodRepository moodRepository;
    private UserRepository userRepository;
    private AuthService authService;


    @Autowired
    public PostService(LoggedUser loggedUser, PostRepository postRepository, MoodRepository moodRepository, UserRepository userRepository, AuthService authService) {
        this.loggedUser = loggedUser;
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public boolean registerPost(AddPostDto addPostDto) {

        Mood mood = this.moodRepository.findByName(addPostDto.getMood());

        Optional<User> byId = this.userRepository.findById(loggedUser.getId());

        if (byId.isEmpty()) {
            return false;
        }

        Post post = new Post();

        post.setContent(addPostDto.getContent());
        post.setMood(mood);
        post.setUser(byId.get());

        this.postRepository.save(post);

        return true;
    }

    public Set<Post> getUserPost() {

        return this.postRepository.findALlByUserId(loggedUser.getId());
    }

    public void removePost(Long id) {
        postRepository.deleteById(id);
    }

    public Set<Post> getOtherUsersPosts(Long id) {
        return this.postRepository.findAllByUserIdNot(id);
    }

    public void addUserLikes(Long id) {

        User user = this.authService.findUserById(loggedUser.getId());
        Post post = this.postRepository.findById(id).get();
        post.getUserLikes().add(user);
        this.postRepository.save(post);

    }
}
