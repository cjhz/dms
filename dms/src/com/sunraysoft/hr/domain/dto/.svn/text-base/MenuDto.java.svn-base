package com.sunraysoft.hr.domain.dto;


public class MenuDto implements Cloneable {
	
	static MenuComparator<MenuDto> menuPositionComparator = new MenuComparator<MenuDto>();
	
	public static MenuComparator<MenuDto> getMenuPositionComparator() {
		return menuPositionComparator;
	}
	
	private Long id;
	private Long bizId;
	private String name;
	private String url;
	private Integer type = 0; //1, 2, 3, 4, 5
	private String memo;
	private boolean checked;
	private Long count = 0L;
	private Long position = 0L;
	
	public MenuDto() {
		super();
	}
	public MenuDto(Long id, String name, String url, Integer type, String memo) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.memo = memo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBizId() {
		return bizId;
	}
	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getPosition() {
		return position;
	}
	public void setPosition(Long position) {
		this.position = position;
	}
	public MenuDto deepCopy() {
		MenuDto copy = null;
		try {
			copy = (MenuDto) clone();
		} catch(Exception e) {
			throw new IllegalArgumentException(e);
		}
		
		return copy; 
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bizId == null) ? 0 : bizId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MenuDto other = (MenuDto) obj;
		if (bizId == null) {
			if (other.bizId != null)
				return false;
		} else if (!bizId.equals(other.bizId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	} 
}
