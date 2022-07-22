package application;

import java.util.ArrayList;

public class Users {
	private String name;
	private String password;
	private boolean isClubMember;
	private boolean isAdmin;
	
	
	public Users(String name, String password, boolean isClubMember,boolean isAdmin) {
		this.name=name;
		this.password=password;
		this.isClubMember=isClubMember;
		this.isAdmin=isAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsClubMember() {
		return isClubMember;
	}

	public void setIsClubMember(boolean isClubMember) {
		this.isClubMember = isClubMember;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }

        if (!(o instanceof Users)) {
            return false;
        }

        Users u = (Users) o;

        return getName().equals(u.getName());
    }
	@Override
	public String toString() {
		return getName();
	}
	
 
}
