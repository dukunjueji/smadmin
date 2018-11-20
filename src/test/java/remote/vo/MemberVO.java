package remote.vo;

import java.io.Serializable;

/**
 * @Author: 余旭东
 * @Date: 2018/11/16 15:20
 * @Description:
 */
public class MemberVO implements Serializable {

    private static final long serialVersionUID = 2409709929619898941L;

    /**
     * 会员id
     */
    private Long id;
    /**
     * 密码
     */
    private String password;

    /**
     * 会员电话
     */
    private String telephone;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

