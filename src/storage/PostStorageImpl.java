package storage;

import exception.PostNotFoundException;
import model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostStorageImpl implements PostStorage {

    List<Post> posts = new ArrayList<>();

    @Override
    public void add(Post post) {

        posts.add(post);
    }


    public Post getPostByTitle(String title) throws PostNotFoundException {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getTitle().equals(title)) {
                return posts.get(i);
            }
        }
        throw new PostNotFoundException("Post not found");
    }

    @Override
    public void searchPostsByKeyword(String keyword) {
        int tmp = 0;
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getTitle().contains(keyword) || posts.get(i).getText().contains(keyword)) {
                System.out.println(posts.get(i));
            } else {
                tmp = 1;
            }
        }
        if (tmp == 1 || posts.size() == 0) {
            System.out.println("not found");
        }
    }

    @Override
    public void printAllPosts() {
        for (int i = 0; i < posts.size(); i++) {
            System.out.println(posts.get(i));

        }

    }

    @Override
    public void printPostsByCategory(String category) {
        System.out.println("Please enter category");
        int tmp = 0;
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getTitle().equals(category) || posts.get(i).getText().equals(category)) {
                System.out.println(posts.get(i));
            } else {
                tmp = -1;
            }
        }
        if (tmp == -1 || posts.size() == 0) {
            System.out.println("not found");
        }
    }
}