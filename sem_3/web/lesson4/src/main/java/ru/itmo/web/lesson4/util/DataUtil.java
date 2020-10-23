package ru.itmo.web.lesson4.util;

import ru.itmo.web.lesson4.model.Post;
import ru.itmo.web.lesson4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov"),
            new User(6, "pashka", "Pavel Mavrin"),
            new User(9, "geranazarov555", "Georgiy Nazarov"),
            new User(11, "tourist", "Gennady Korotkevich")
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(12, "Codeforces Round #676 (Div. 2)", "How dark. You fight like a jolly roger. Arrr, coal-black punishment!Yuck, yo-ho-ho. The real ship oppressively ransacks the lagoon. Danistas unda in aetheres!, Well, addled sea. go to haiti. Stormy, misty cannons darkly mark a jolly, rough bucaneer.", 83677),
            new Post(10, "Codeforces Round #677 (Div. 3)", "Try chopping pickles fritters rinseed with lemon juice. Remember: choped chickpeas taste best when toasted in a pan soaked with black cardamon. Sliced, hardened pudding is best whisked with instant salsa verde. Toss the onion with mild black cardamon, brine, parsley, and green curry making sure to cover all of it.", 83841),
            new Post(9, "Трансляции на Codeforces", "Terror regius deus est.This ellipse has only been yearned by an ancient alien. All the mysteries will be lost in hypnosis like voyages in energies I capture this pattern, it's called fantastic adventure. Die without vision, and we won’t gather a pathway. Shine and you will be witnessed cheerfully.", 83939),
            new Post(1, "Технокубок 2021 — олимпиада по программированию для школьников", "Life is not private in closest places, the body of happiness, or hell, but everywhere. Squeeze butter quickly, then mix with fish sauce and serve carefully ground in wok. Try sliceing cake whisked with beer, soaked with cinnamon. Watermelon can be enameled with quartered eggs, also try jumbleing the pilaf with fish sauce. When heatting puréed tunas, be sure they are room temperature. Remember: drained nachos taste best when pressed in a casserole enameled with cinnamon. Rinse the okra with puréed cumin, radish sprouts, curry, and rum making sure to cover all of it.", 83936)
    );



    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("posts", POSTS);
        data.put("users", USERS);
//        for (User user : USERS) {
//            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
//                data.put("user", user);
//            }
//        }

    }
}
