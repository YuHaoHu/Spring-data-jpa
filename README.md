# Spring-data-jpa
Spring-data-jpa作业

##jpa-data-demo5中部分代码不能实现，然后用@query注解之后，所有的测试方法都崩溃。

##jpa-data-demo6工程中实现了如下功能：一对多的测试，多对一的测试

  JpaRepository:删除
  
  JpaRepository:增加
  
  JpaRepository:修改
  
  JpaRepository:查询
  
  测试@Query 查询 JPQL
  
  Repository测试--根据姓名查询
  
  Repository测试--根据姓名和年龄查询
  
  pagingAndShortRepository--排序
  
  PagingAndShortRepository--分页
  
  PagingAndShortRepository--排序与分页
  
  JpaRepository--排序
  
  JpaSpecificationExecutor和JpaRepository 测试--单个条件查询
      @Test
    public void testUserRepositoryJpa() {
        
        /**
         *  Predicate :封装了单个的查询条件
         *  root:查询对象的属性封装
         *  CriteriaQuery<?> criteriaQuery：我们要执行的查询中的各个部分的信息：select ，from
         *  CriteriaBuilder criteriaBuilder:查询条件的构造器。定义不同的查询条件
         */
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                Predicate pre = criteriaBuilder.equal(root.get("name"), "胡玉浩");
                return pre;
            }
        };
        List<User> list = userRepositoryJpa.findAll(spec);
        for (User user : list
                ) {
            System.out.println(user);
        }
    }
    
    /**
     * JpaSpecificationExecutor和JpaRepository
     * 测试--多个条件查询:第一种方法
     */
    @Test
    public void testUserRepositoryJpa2() {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder
                    cb) {
                //WHERE name = ? AND id = ?
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("name"), "张三"));
                list.add(cb.equal(root.get("age"), 15));
                Predicate[] arr = new Predicate[list.size()];
                return cb.and(list.toArray(arr));
            }
        };
        List<User> list = userRepositoryJpa.findAll(spec);
        for (User user : list
                ) {
            System.out.println(user);
        }
        
    }
    
    /**
     * JpaSpecificationExecutor和JpaRepository 测试--多个条件查询:第二种方法
     */
    @Test
    public void testUserRepositoryJpa3() {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder cb) {
                //WHERE name = ? AND id = ?

                return cb.and(cb.equal(root.get("name"), "张三"), cb.equal(root
                        .get("age"), 15));
            }
        };
        List<User> list = userRepositoryJpa.findAll(spec);
        for (User user : list
                ) {
            System.out.println(user);
        }
    }
    
    /**
     * JpaSpecificationExecutor和JpaRepository 测试--多个条件查询:并进行分页和倒叙操作
     */
    @Test
    public void testUserRepositoryJpa4() {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder cb) {
                //WHERE（ name = ? AND id = ?）or(id=?)
                return cb.or(cb.and(cb.equal(root.get("name"), "张三"),
                        cb.equal(root.get("age"), 15))
                        , cb.equal(root.get("id"), 1));
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 2, sort);
        Page<User> page = this.userRepositoryJpa.findAll(spec, pageable);
        System.out.println("每一页显示的信息条数:" + page.getTotalPages());  ///每一页显示的信息条数
        System.out.println("一共多少数据:" + page.getTotalElements());  //一共多少数据
        List<User> list = page.getContent();
        for (User user : list
                ) {
            System.out.println(user);
        }
        
    }
}

            
