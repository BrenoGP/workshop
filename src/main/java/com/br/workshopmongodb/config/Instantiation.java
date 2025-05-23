package com.br.workshopmongodb.config;

import com.br.workshopmongodb.dto.AuthorDTO;
import com.br.workshopmongodb.dto.CommentDTO;
import com.br.workshopmongodb.entity.Post;
import com.br.workshopmongodb.entity.User;
import com.br.workshopmongodb.repository.PostRepository;
import com.br.workshopmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

     userRepository.deleteAll();
     postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post1 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem, mano!", sdf.parse("21/12/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("21/12/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("21/12/2018"), new AuthorDTO(alex));

        post.getComments().addAll(Arrays.asList(c1, c2));
        post1.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post, post1 ));

        maria.getPosts().addAll(Arrays.asList(post, post1));
        userRepository.save(maria);
    }


}
