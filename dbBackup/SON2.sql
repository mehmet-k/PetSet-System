PGDMP         #                 |            PETSHOP    15.4    15.4 ?    a           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            b           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            c           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            d           1262    24762    PETSHOP    DATABASE     }   CREATE DATABASE "PETSHOP" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_Turkey.1254';
    DROP DATABASE "PETSHOP";
                postgres    false            �            1255    33337 /   insert_into_adoption_requests(integer, integer)    FUNCTION     �   CREATE FUNCTION public.insert_into_adoption_requests(applicant_id integer, pet_id integer) RETURNS text
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO adoption_requests(applicantid,petid)
	VALUES (applicant_id,pet_id);
	
    RETURN 'done';
END;
$$;
 Z   DROP FUNCTION public.insert_into_adoption_requests(applicant_id integer, pet_id integer);
       public          postgres    false            �            1255    33099 /   insert_into_user_has_this_pet(integer, integer)    FUNCTION     �   CREATE FUNCTION public.insert_into_user_has_this_pet(userid integer, petid integer) RETURNS text
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO user_has_this_pet(userid, petid)
    VALUES (userid,petid);
	
	RETURN 'true';
END;
$$;
 S   DROP FUNCTION public.insert_into_user_has_this_pet(userid integer, petid integer);
       public          postgres    false            �            1255    33416 ,   insert_into_user_purchases(integer, integer)    FUNCTION     �   CREATE FUNCTION public.insert_into_user_purchases(userid integer, itemid integer) RETURNS text
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO user_purchases(userid,itemid)
    VALUES (userID,itemID);
	RETURN 'done';
END;
$$;
 Q   DROP FUNCTION public.insert_into_user_purchases(userid integer, itemid integer);
       public          postgres    false            �            1255    33351 5   insert_into_user_purchases(integer, integer, integer)    FUNCTION       CREATE FUNCTION public.insert_into_user_purchases(userid integer, itemid integer, price integer) RETURNS text
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO user_purchases(userid,itemid,purchasedate,price)
    VALUES (userID,itemID,DATE,price);
	RETURN 'done';
END;
$$;
 `   DROP FUNCTION public.insert_into_user_purchases(userid integer, itemid integer, price integer);
       public          postgres    false            �            1259    32988    pet_type_id_seq    SEQUENCE     x   CREATE SEQUENCE public.pet_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.pet_type_id_seq;
       public          postgres    false            �            1259    33006    pet    TABLE       CREATE TABLE public.pet (
    id integer DEFAULT nextval('public.pet_type_id_seq'::regclass) NOT NULL,
    pettypeid integer,
    pettype character varying(255) NOT NULL,
    petname character varying(255) NOT NULL,
    status integer DEFAULT 1,
    isadopted integer
);
    DROP TABLE public.pet;
       public         heap    postgres    false    219            �            1255    33363 +   list_animals_by_pet_type(character varying)    FUNCTION     6  CREATE FUNCTION public.list_animals_by_pet_type(name character varying) RETURNS SETOF public.pet
    LANGUAGE plpgsql
    AS $$
DECLARE
    ani_record pet;
BEGIN
    FOR ani_record IN SELECT petname, id FROM pet WHERE pettype = name
    LOOP
        RETURN NEXT ani_record;
    END LOOP;

    RETURN;
END;
$$;
 G   DROP FUNCTION public.list_animals_by_pet_type(name character varying);
       public          postgres    false    222            �            1255    33342 %   trig_func_clear_user_related_tables()    FUNCTION     �  CREATE FUNCTION public.trig_func_clear_user_related_tables() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare 

begin  
	update pet
	set status  = 0
	from user_has_this_pet uhtp, pet p
	where  new.status = 0 and uhtp.userid = new.id and uhtp.petid = p.id ;
	
	update user_has_this_pet
	set status = 0
	where  new.status = 0 and userid = new.id;
		
	update user_purchases
	set status = 0
	where  new.status = 0 and userid = new.id;
	
	return new;
end;
$$;
 <   DROP FUNCTION public.trig_func_clear_user_related_tables();
       public          postgres    false            �            1255    33365 +   trig_func_remove_adoption_requests_on_pet()    FUNCTION     B  CREATE FUNCTION public.trig_func_remove_adoption_requests_on_pet() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    -- When a pet row is updated, set all adoption_requests statuses to 0
    UPDATE adoption_requests
    SET status = 0
    WHERE petid = OLD.id;
	RAISE NOTICE 'ads removed';
    RETURN NEW;
END;
$$;
 B   DROP FUNCTION public.trig_func_remove_adoption_requests_on_pet();
       public          postgres    false            �            1259    33394    admin    TABLE     U   CREATE TABLE public.admin (
    id integer NOT NULL,
    status integer DEFAULT 1
);
    DROP TABLE public.admin;
       public         heap    postgres    false            �            1259    33123    adoption_requests    TABLE     t   CREATE TABLE public.adoption_requests (
    applicantid integer,
    petid integer,
    status integer DEFAULT 1
);
 %   DROP TABLE public.adoption_requests;
       public         heap    postgres    false            �            1259    32976    item_id_seq    SEQUENCE     t   CREATE SEQUENCE public.item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.item_id_seq;
       public          postgres    false            �            1259    32969    item_type_id_seq    SEQUENCE     y   CREATE SEQUENCE public.item_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.item_type_id_seq;
       public          postgres    false            �            1259    32970 
   item_types    TABLE     �   CREATE TABLE public.item_types (
    id integer DEFAULT nextval('public.item_type_id_seq'::regclass) NOT NULL,
    itemtype character varying(255) NOT NULL,
    status integer
);
    DROP TABLE public.item_types;
       public         heap    postgres    false    216            �            1259    33019    items    TABLE       CREATE TABLE public.items (
    id integer DEFAULT nextval('public.item_id_seq'::regclass) NOT NULL,
    itemtypeid integer NOT NULL,
    itemtype character varying(255) NOT NULL,
    itemname character varying(255) NOT NULL,
    status integer DEFAULT 1,
    price integer
);
    DROP TABLE public.items;
       public         heap    postgres    false    218            �            1259    32989 
   pet_id_seq    SEQUENCE     s   CREATE SEQUENCE public.pet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.pet_id_seq;
       public          postgres    false            �            1259    32990 	   pet_types    TABLE     �   CREATE TABLE public.pet_types (
    id integer DEFAULT nextval('public.pet_type_id_seq'::regclass) NOT NULL,
    pettype character varying(255) NOT NULL,
    status integer DEFAULT 1
);
    DROP TABLE public.pet_types;
       public         heap    postgres    false    219            �            1259    24787    user_id_seq    SEQUENCE     t   CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false            �            1259    24788    users    TABLE     =  CREATE TABLE public.users (
    id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    username character varying(255),
    firstname character varying(255),
    surname character varying(255),
    address character varying(255),
    status integer DEFAULT 1,
    password character varying(255)
);
    DROP TABLE public.users;
       public         heap    postgres    false    214            �            1259    33354    select_all_users    VIEW     �   CREATE VIEW public.select_all_users AS
 SELECT users.id,
    users.username,
    users.firstname,
    users.surname,
    users.address,
    users.status,
    users.password
   FROM public.users;
 #   DROP VIEW public.select_all_users;
       public          postgres    false    215    215    215    215    215    215    215            �            1259    33044    user_has_this_pet    TABLE     x   CREATE TABLE public.user_has_this_pet (
    petid integer NOT NULL,
    status integer DEFAULT 1,
    userid integer
);
 %   DROP TABLE public.user_has_this_pet;
       public         heap    postgres    false            �            1259    33061    user_purchases    TABLE     �   CREATE TABLE public.user_purchases (
    userid integer NOT NULL,
    itemid integer NOT NULL,
    purchasedate date,
    status integer DEFAULT 1
);
 "   DROP TABLE public.user_purchases;
       public         heap    postgres    false            ^          0    33394    admin 
   TABLE DATA           +   COPY public.admin (id, status) FROM stdin;
    public          postgres    false    228   �R       ]          0    33123    adoption_requests 
   TABLE DATA           G   COPY public.adoption_requests (applicantid, petid, status) FROM stdin;
    public          postgres    false    226   S       T          0    32970 
   item_types 
   TABLE DATA           :   COPY public.item_types (id, itemtype, status) FROM stdin;
    public          postgres    false    217   FS       Z          0    33019    items 
   TABLE DATA           R   COPY public.items (id, itemtypeid, itemtype, itemname, status, price) FROM stdin;
    public          postgres    false    223   �S       Y          0    33006    pet 
   TABLE DATA           Q   COPY public.pet (id, pettypeid, pettype, petname, status, isadopted) FROM stdin;
    public          postgres    false    222   $T       X          0    32990 	   pet_types 
   TABLE DATA           8   COPY public.pet_types (id, pettype, status) FROM stdin;
    public          postgres    false    221   �T       [          0    33044    user_has_this_pet 
   TABLE DATA           B   COPY public.user_has_this_pet (petid, status, userid) FROM stdin;
    public          postgres    false    224   �T       \          0    33061    user_purchases 
   TABLE DATA           N   COPY public.user_purchases (userid, itemid, purchasedate, status) FROM stdin;
    public          postgres    false    225   BU       R          0    24788    users 
   TABLE DATA           \   COPY public.users (id, username, firstname, surname, address, status, password) FROM stdin;
    public          postgres    false    215   jU       e           0    0    item_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.item_id_seq', 33, true);
          public          postgres    false    218            f           0    0    item_type_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.item_type_id_seq', 5, true);
          public          postgres    false    216            g           0    0 
   pet_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.pet_id_seq', 1, false);
          public          postgres    false    220            h           0    0    pet_type_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.pet_type_id_seq', 41, true);
          public          postgres    false    219            i           0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 34, true);
          public          postgres    false    214            �           2606    32975    item_types item_types_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.item_types
    ADD CONSTRAINT item_types_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.item_types DROP CONSTRAINT item_types_pkey;
       public            postgres    false    217            �           2606    33026    items items_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.items DROP CONSTRAINT items_pkey;
       public            postgres    false    223            �           2606    33013    pet pet_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.pet DROP CONSTRAINT pet_pkey;
       public            postgres    false    222            �           2606    32997    pet_types pet_types_pettype_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.pet_types
    ADD CONSTRAINT pet_types_pettype_key UNIQUE (pettype);
 I   ALTER TABLE ONLY public.pet_types DROP CONSTRAINT pet_types_pettype_key;
       public            postgres    false    221            �           2606    32995    pet_types pet_types_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.pet_types
    ADD CONSTRAINT pet_types_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.pet_types DROP CONSTRAINT pet_types_pkey;
       public            postgres    false    221            �           2606    33399    admin unique_user_id 
   CONSTRAINT     M   ALTER TABLE ONLY public.admin
    ADD CONSTRAINT unique_user_id UNIQUE (id);
 >   ALTER TABLE ONLY public.admin DROP CONSTRAINT unique_user_id;
       public            postgres    false    228            �           2606    33050 -   user_has_this_pet user_has_this_pet_petid_key 
   CONSTRAINT     i   ALTER TABLE ONLY public.user_has_this_pet
    ADD CONSTRAINT user_has_this_pet_petid_key UNIQUE (petid);
 W   ALTER TABLE ONLY public.user_has_this_pet DROP CONSTRAINT user_has_this_pet_petid_key;
       public            postgres    false    224            �           2606    33067 (   user_purchases user_purchases_itemid_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.user_purchases
    ADD CONSTRAINT user_purchases_itemid_key UNIQUE (itemid);
 R   ALTER TABLE ONLY public.user_purchases DROP CONSTRAINT user_purchases_itemid_key;
       public            postgres    false    225            �           2606    33065 (   user_purchases user_purchases_userid_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.user_purchases
    ADD CONSTRAINT user_purchases_userid_key UNIQUE (userid);
 R   ALTER TABLE ONLY public.user_purchases DROP CONSTRAINT user_purchases_userid_key;
       public            postgres    false    225            �           2606    24795    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    215            �           1259    33415    user_has_this_pet_userid_key    INDEX     [   CREATE INDEX user_has_this_pet_userid_key ON public.user_has_this_pet USING btree (petid);
 0   DROP INDEX public.user_has_this_pet_userid_key;
       public            postgres    false    224            �           2620    33350 $   users trig_clear_user_related_tables    TRIGGER     �   CREATE TRIGGER trig_clear_user_related_tables AFTER UPDATE ON public.users FOR EACH ROW WHEN ((old.id IS NOT NULL)) EXECUTE FUNCTION public.trig_func_clear_user_related_tables();
 =   DROP TRIGGER trig_clear_user_related_tables ON public.users;
       public          postgres    false    233    215    215            �           2620    33366 /   pet trig_remove_adoption_requests_on_pet_update    TRIGGER     �   CREATE TRIGGER trig_remove_adoption_requests_on_pet_update AFTER UPDATE ON public.pet FOR EACH ROW WHEN ((old.id IS NOT NULL)) EXECUTE FUNCTION public.trig_func_remove_adoption_requests_on_pet();
 H   DROP TRIGGER trig_remove_adoption_requests_on_pet_update ON public.pet;
       public          postgres    false    222    222    232            �           2606    33400    admin admin_id_fkey    FK CONSTRAINT        ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_id_fkey FOREIGN KEY (id) REFERENCES public.users(id) ON DELETE CASCADE;
 =   ALTER TABLE ONLY public.admin DROP CONSTRAINT admin_id_fkey;
       public          postgres    false    228    3234    215            �           2606    33131 4   adoption_requests adoption_requests_applicantid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.adoption_requests
    ADD CONSTRAINT adoption_requests_applicantid_fkey FOREIGN KEY (applicantid) REFERENCES public.users(id) ON DELETE CASCADE;
 ^   ALTER TABLE ONLY public.adoption_requests DROP CONSTRAINT adoption_requests_applicantid_fkey;
       public          postgres    false    3234    226    215            �           2606    33136 .   adoption_requests adoption_requests_petid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.adoption_requests
    ADD CONSTRAINT adoption_requests_petid_fkey FOREIGN KEY (petid) REFERENCES public.pet(id) ON DELETE CASCADE;
 X   ALTER TABLE ONLY public.adoption_requests DROP CONSTRAINT adoption_requests_petid_fkey;
       public          postgres    false    3242    222    226            �           2606    33027    items items_itemtypeid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_itemtypeid_fkey FOREIGN KEY (itemtypeid) REFERENCES public.item_types(id) ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.items DROP CONSTRAINT items_itemtypeid_fkey;
       public          postgres    false    217    3236    223            �           2606    33014    pet pet_pettypeid_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_pettypeid_fkey FOREIGN KEY (pettypeid) REFERENCES public.pet_types(id);
 @   ALTER TABLE ONLY public.pet DROP CONSTRAINT pet_pettypeid_fkey;
       public          postgres    false    3240    221    222            �           2606    33056 .   user_has_this_pet user_has_this_pet_petid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_has_this_pet
    ADD CONSTRAINT user_has_this_pet_petid_fkey FOREIGN KEY (petid) REFERENCES public.pet(id);
 X   ALTER TABLE ONLY public.user_has_this_pet DROP CONSTRAINT user_has_this_pet_petid_fkey;
       public          postgres    false    222    224    3242            �           2606    33410 /   user_has_this_pet user_has_this_pet_userid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_has_this_pet
    ADD CONSTRAINT user_has_this_pet_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);
 Y   ALTER TABLE ONLY public.user_has_this_pet DROP CONSTRAINT user_has_this_pet_userid_fkey;
       public          postgres    false    215    3234    224            �           2606    33405 0   user_has_this_pet user_has_this_pet_usertid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_has_this_pet
    ADD CONSTRAINT user_has_this_pet_usertid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);
 Z   ALTER TABLE ONLY public.user_has_this_pet DROP CONSTRAINT user_has_this_pet_usertid_fkey;
       public          postgres    false    224    3234    215            �           2606    33073 )   user_purchases user_purchases_itemid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_purchases
    ADD CONSTRAINT user_purchases_itemid_fkey FOREIGN KEY (itemid) REFERENCES public.items(id);
 S   ALTER TABLE ONLY public.user_purchases DROP CONSTRAINT user_purchases_itemid_fkey;
       public          postgres    false    3244    223    225            �           2606    33068 )   user_purchases user_purchases_userid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_purchases
    ADD CONSTRAINT user_purchases_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);
 S   ALTER TABLE ONLY public.user_purchases DROP CONSTRAINT user_purchases_userid_fkey;
       public          postgres    false    225    3234    215            ^      x�36�4�261z\\\ [�      ]   2   x�32�46�4�26�4���&����s�6��y �H��qqq ��
�      T   9   x�3�LN,QH��O�4�2�L�O�q�9�2�R`<δ��ϔ�(1))�1F��� vB      Z   �   x�m�1�0����������) uI�
Y�z��b�������
�i+%�V�7��B�g=�Hг-�rpH�Y�΂FЃsMZ�,m$/�F�[�}��"�7�J�M&x�c֞�N��eM{y��1�Amc      Y   �   x�36�4�LN,�t)�M����M�B!�ٙE!SNCSΔ�tN���23��oiZZ&T��7 �(�4�26�44�L�,J�tJ<2?'17��.�X��j�J�,�hb �% 1�4"f�N,������ B�/z      X   5   x�34�L�,J�4�24�,JLJ�,�-8�2�3@,��D��)gJ~:����� g�*      [   4   x�-��  �w3�Gم��@�Ǥ����^:��:�ԧ�]��6 �;�      \      x�36�42���4����� �r      R   '  x�mQ�n�@=�~LS���H-I[��[2�4l`3�1�3�B���h�$=�余y�^v�WhɵkGl�$B�w�-�0L��6�ܮ�B����q2�2X��׫�Wa�k<"d�1����\(�~��f�z��Kr�m�7X�+�+�W�{�@F3`W�n`+1�T�*�PQN����]?̔?�2tTˬ��`!��A���aA&#Ng0o�:H����f���3xk���ȧ�Mړ%�?�5䶿1���RB�K�����}�CC��b1�y�/��~P���..n40ĺi��R�t�,     