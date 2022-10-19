package br.edu.uniritter.mobile.mobile20222_1.model;

import android.os.Parcel;
import android.os.Parcelable;

import br.edu.uniritter.mobile.mobile20222_1.repository.UserRepository;

public class Post implements Parcelable {

    private int id;
    private String title;
    private String body;
    private User user;

    public Post(int id, String title, String body, int idUser) {
        this.id = id;
        this.title = title;
        this.body = body;
        // to-do: retirar a dependência do repositório na Model
        this.user = UserRepository.getInstance().getUserById(idUser);
    }

    protected Post(Parcel in) {
        id = in.readInt();
        title = in.readString();
        body = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeParcelable(user,0);
    }
}
