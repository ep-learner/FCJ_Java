	/**
     * user属性：
     * id name password		
     */
	public void add(Review bean){
        String sql = "insert into Review values(null,?,?)";
		
        try {
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void delete(int id){
		String sql = "delete from User where id = "+id;
		
        try {
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }		
    }
    public void update(User bean){
		String sql = "update  User set name = ?,passwd = ? where id = ?";
        try {
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User get(int id){
		String sql = "select *  from  User where id = " +id;
        try {
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getTotal(int id){
        String sql = "select count(*)  from  User ";
        try {
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
    }

    public List<User> list(int start, int count){
		String sql = "select *  from  User order by id desc limit ?,? ";
        try {
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<User> list(){
        return list(0,Short.MAX_VALUE);
    }

    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
        User u = new User();
        ud.add(u);
        ud.update(u);
        ud.get(1);
        ud.delete(1);
    }