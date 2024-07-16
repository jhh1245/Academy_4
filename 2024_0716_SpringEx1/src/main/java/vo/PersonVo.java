package vo;

public class PersonVo {
	
	String  name;
	int 	age;
	String  addr;
	
	
	
	public PersonVo() {
		System.out.println("-- PersonVo() -- ");
	}


	public PersonVo(String name, int age, String addr) {
		super();
		System.out.println("-- PersonVo(name, age, addr) --");
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		/* System.out.println("setter 호출"); */
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}
