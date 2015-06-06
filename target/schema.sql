
    alter table groupmember 
        drop 
        foreign key FK_rwqtu9dvvniyo4v5fcamoh04y;

    alter table groupmember 
        drop 
        foreign key FK_4ef4sxg77350363nfui8r376v;

    alter table news 
        drop 
        foreign key FK_ix5bx875hxiu5bsxs6b7t1fuh;

    alter table news 
        drop 
        foreign key FK_gb6te5yfbm7hl4hnspn4dgir8;

    alter table newsViewGroups 
        drop 
        foreign key FK_cn6rrpjl6kiwf4ad3hjvg7k41;

    alter table newsViewGroups 
        drop 
        foreign key FK_gtto3t3dfxqf70lakgr2psn95;

    alter table newsViewUsers 
        drop 
        foreign key FK_dkr2cf142ls02loumwcrs5t9y;

    alter table newsViewUsers 
        drop 
        foreign key FK_9j8a0clax1pq8dit4jn94660s;

    alter table newstype 
        drop 
        foreign key FK_1vbql25co889qk6rtclyhf6lb;

    alter table newstype 
        drop 
        foreign key FK_hmgkgk8k2pt0vckrf0ojvervw;

    alter table post 
        drop 
        foreign key FK_cm4qxgcbj1e8rnpsu5lc4jcp9;

    alter table post 
        drop 
        foreign key FK_5fy9wlnkytsbiugr238w7r0o7;

    alter table post 
        drop 
        foreign key FK_c7j4qhm9il75ky3apra9nqk7y;

    alter table postReplyGroups 
        drop 
        foreign key FK_3wymh8hxmdfiwi9atvmwp4weh;

    alter table postReplyGroups 
        drop 
        foreign key FK_krpwufo0f3ssnb9imxbwq215u;

    alter table postReplyUsers 
        drop 
        foreign key FK_nv7so24p2615vph9o34pdvlm7;

    alter table postReplyUsers 
        drop 
        foreign key FK_2vaxdgmmjc4e4wme5c4p2g85v;

    alter table posttype 
        drop 
        foreign key FK_ffmfgkasiisydpqauwo2nmt9x;

    alter table posttype 
        drop 
        foreign key FK_9xw0t3e893vd7smxdma4mc3bi;

    alter table replies 
        drop 
        foreign key FK_gdp4ojlxd122nfsgpgpp2rm3d;

    alter table replies 
        drop 
        foreign key FK_kjvbq8o7c5gint2ht6a72vlcr;

    alter table user_role 
        drop 
        foreign key FK_it77eq964jhfqtu54081ebtio;

    alter table user_role 
        drop 
        foreign key FK_apcc8lxk2xnug8377fatvbn04;

    drop table if exists app_user;

    drop table if exists groupmember;

    drop table if exists news;

    drop table if exists newsViewGroups;

    drop table if exists newsViewUsers;

    drop table if exists newstype;

    drop table if exists post;

    drop table if exists postReplyGroups;

    drop table if exists postReplyUsers;

    drop table if exists posttype;

    drop table if exists replies;

    drop table if exists role;

    drop table if exists user_role;

    drop table if exists usergroup;

    create table app_user (
        id bigint not null auto_increment,
        account_expired bit not null,
        account_locked bit not null,
        address varchar(150),
        city varchar(50),
        country varchar(100),
        postal_code varchar(15),
        province varchar(100),
        credentials_expired bit not null,
        email varchar(255) not null,
        account_enabled bit,
        first_name varchar(50) not null,
        last_name varchar(50) not null,
        password varchar(255) not null,
        password_hint varchar(255),
        phone_number varchar(255),
        username varchar(50) not null,
        version integer,
        website varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table groupmember (
        group_id bigint not null,
        user_id bigint not null,
        primary key (group_id, user_id)
    ) ENGINE=InnoDB;

    create table news (
        id bigint not null auto_increment,
        content varchar(255),
        createTime datetime,
        expiredTime datetime,
        ifAccessLimited bit not null,
        thumbnailUrl varchar(255),
        title varchar(255),
        updateTime datetime,
        creater_id bigint,
        newsType_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table newsViewGroups (
        news_id bigint not null,
        group_id bigint not null,
        primary key (news_id, group_id)
    ) ENGINE=InnoDB;

    create table newsViewUsers (
        news_id bigint not null,
        user_id bigint not null,
        primary key (news_id, user_id)
    ) ENGINE=InnoDB;

    create table newstype (
        id bigint not null auto_increment,
        comment varchar(255),
        createTime datetime,
        name varchar(255),
        updateTime datetime,
        creater_id bigint,
        updater_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table post (
        id bigint not null auto_increment,
        content varchar(255),
        createTime datetime,
        ifAccessAllReply bit not null,
        lastReplyTime datetime,
        thumbnailUrl varchar(255),
        title varchar(255),
        updateTime datetime,
        creater_id bigint,
        lastReplier_id bigint,
        posttype_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table postReplyGroups (
        post_id bigint not null,
        group_id bigint not null,
        primary key (post_id, group_id)
    ) ENGINE=InnoDB;

    create table postReplyUsers (
        post_id bigint not null,
        user_id bigint not null,
        primary key (post_id, user_id)
    ) ENGINE=InnoDB;

    create table posttype (
        id bigint not null auto_increment,
        comment varchar(255),
        createTime datetime,
        name varchar(255),
        updateTime datetime,
        creater_id bigint,
        updater_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table replies (
        id bigint not null auto_increment,
        content varchar(255),
        replyTime datetime,
        post_id bigint,
        replier_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table role (
        id bigint not null auto_increment,
        description varchar(64),
        name varchar(20),
        primary key (id)
    ) ENGINE=InnoDB;

    create table user_role (
        user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    ) ENGINE=InnoDB;

    create table usergroup (
        id bigint not null auto_increment,
        comment varchar(255),
        createTime datetime,
        name varchar(255),
        updateTime datetime,
        primary key (id)
    ) ENGINE=InnoDB;

    alter table app_user 
        add constraint UK_1j9d9a06i600gd43uu3km82jw  unique (email);

    alter table app_user 
        add constraint UK_3k4cplvh82srueuttfkwnylq0  unique (username);

    alter table groupmember 
        add constraint FK_rwqtu9dvvniyo4v5fcamoh04y 
        foreign key (user_id) 
        references app_user (id);

    alter table groupmember 
        add constraint FK_4ef4sxg77350363nfui8r376v 
        foreign key (group_id) 
        references usergroup (id);

    alter table news 
        add constraint FK_ix5bx875hxiu5bsxs6b7t1fuh 
        foreign key (creater_id) 
        references app_user (id);

    alter table news 
        add constraint FK_gb6te5yfbm7hl4hnspn4dgir8 
        foreign key (newsType_id) 
        references newstype (id);

    alter table newsViewGroups 
        add constraint FK_cn6rrpjl6kiwf4ad3hjvg7k41 
        foreign key (group_id) 
        references usergroup (id);

    alter table newsViewGroups 
        add constraint FK_gtto3t3dfxqf70lakgr2psn95 
        foreign key (news_id) 
        references news (id);

    alter table newsViewUsers 
        add constraint FK_dkr2cf142ls02loumwcrs5t9y 
        foreign key (user_id) 
        references app_user (id);

    alter table newsViewUsers 
        add constraint FK_9j8a0clax1pq8dit4jn94660s 
        foreign key (news_id) 
        references news (id);

    alter table newstype 
        add constraint FK_1vbql25co889qk6rtclyhf6lb 
        foreign key (creater_id) 
        references app_user (id);

    alter table newstype 
        add constraint FK_hmgkgk8k2pt0vckrf0ojvervw 
        foreign key (updater_id) 
        references app_user (id);

    alter table post 
        add constraint FK_cm4qxgcbj1e8rnpsu5lc4jcp9 
        foreign key (creater_id) 
        references app_user (id);

    alter table post 
        add constraint FK_5fy9wlnkytsbiugr238w7r0o7 
        foreign key (lastReplier_id) 
        references app_user (id);

    alter table post 
        add constraint FK_c7j4qhm9il75ky3apra9nqk7y 
        foreign key (posttype_id) 
        references posttype (id);

    alter table postReplyGroups 
        add constraint FK_3wymh8hxmdfiwi9atvmwp4weh 
        foreign key (group_id) 
        references usergroup (id);

    alter table postReplyGroups 
        add constraint FK_krpwufo0f3ssnb9imxbwq215u 
        foreign key (post_id) 
        references post (id);

    alter table postReplyUsers 
        add constraint FK_nv7so24p2615vph9o34pdvlm7 
        foreign key (user_id) 
        references app_user (id);

    alter table postReplyUsers 
        add constraint FK_2vaxdgmmjc4e4wme5c4p2g85v 
        foreign key (post_id) 
        references post (id);

    alter table posttype 
        add constraint FK_ffmfgkasiisydpqauwo2nmt9x 
        foreign key (creater_id) 
        references app_user (id);

    alter table posttype 
        add constraint FK_9xw0t3e893vd7smxdma4mc3bi 
        foreign key (updater_id) 
        references app_user (id);

    alter table replies 
        add constraint FK_gdp4ojlxd122nfsgpgpp2rm3d 
        foreign key (post_id) 
        references post (id);

    alter table replies 
        add constraint FK_kjvbq8o7c5gint2ht6a72vlcr 
        foreign key (replier_id) 
        references app_user (id);

    alter table user_role 
        add constraint FK_it77eq964jhfqtu54081ebtio 
        foreign key (role_id) 
        references role (id);

    alter table user_role 
        add constraint FK_apcc8lxk2xnug8377fatvbn04 
        foreign key (user_id) 
        references app_user (id);
