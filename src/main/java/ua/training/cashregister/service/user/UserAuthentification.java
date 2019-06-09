package ua.training.cashregister.service.user;

import ua.training.cashregister.dao.DaoFactory;
import ua.training.cashregister.dao.UserDao;
import ua.training.cashregister.entity.User;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserAuthentification {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private static final String key = "8F78D28C76F928F7B80FB099983282A5";
    private static final String initVector = "CF65160542FDBAF6";


    public Optional<User> findUser(String username){
        UserDao dao =  daoFactory.createUserDao();
        Optional<User> found =  dao.findByUsername(username);
        dao.close();
        return found;
    }

    public boolean checkAuthority(String username,String password){
        UserDao dao =  daoFactory.createUserDao();
        AtomicBoolean authority = new AtomicBoolean(false);
        findUser(username).ifPresent(user -> {
            authority.set(checkPassword(password, user.getPasswordHash()));});
        dao.close();
        return authority.get();
    }

    public String codePassword(String password){
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private boolean checkPassword(String password,String hash){
        return codePassword(password).equals(hash);
    }
}
