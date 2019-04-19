package http;

import java.util.Objects;

public class QiNiu {
    private String token;
    private String key;

    @Override
    public String toString() {
        return "QiNiu{" +
                "token='" + token + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QiNiu qiNiu = (QiNiu) o;
        return Objects.equals(token, qiNiu.token) &&
                Objects.equals(key, qiNiu.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, key);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
