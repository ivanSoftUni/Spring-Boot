package com.likebookapp.service;

import com.likebookapp.model.dtos.AddPostDto;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {


    private LoggedUser loggedUser;
    private PostRepository postRepository;
    private MoodRepository moodRepository;
    private UserRepository userRepository;


    @Autowired
    public PostService(LoggedUser loggedUser, PostRepository postRepository, MoodRepository moodRepository, UserRepository userRepository) {
        this.loggedUser = loggedUser;
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
        this.userRepository = userRepository;
    }

    public boolean registerPost(AddPostDto addPostDto) {

        Mood mood = this.moodRepository.findByName(addPostDto.getMood());

        Optional<User> byId = this.userRepository.findById(loggedUser.getId());

        if (!byId.isPresent()) {
            return false;
        }
        Post post = new Post();

        post.setContent(addPostDto.getContent());
        post.setMood(mood);
        post.setUser(byId.get());

        this.postRepository.save(post);

        return true;
    }
}
