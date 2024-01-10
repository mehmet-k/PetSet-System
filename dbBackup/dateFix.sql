PGDMP                  
         |            PETSHOP    15.4    15.4 9    T           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            U           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            V           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            W           1262    24762    PETSHOP    DATABASE     }   CREATE DATABASE "PETSHOP" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_Turkey.1254';
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
       public          postgres    false            �            1255    33101 ,   insert_into_user_purchases(integer, integer)    FUNCTION     �   CREATE FUNCTION public.insert_into_user_purchases(userid integer, itemid integer) RETURNS text
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO user_purchases(userid,itemid,purchasedate)
    VALUES (userID,itemID,DATE);
	RETURN 'done';
END;
$$;
 Q   DROP FUNCTION public.insert_into_user_purchases(userid integer, itemid integer);
       public          postgres    false            �            1255    33342 %   trig_func_clear_user_related_tables()    FUNCTION        CREATE FUNCTION public.trig_func_clear_user_related_tables() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare 

begin  
	update pet
	set pet.status  = 0
	from user_has_this_pet uhtp, pet p
	where  new.status = 0 and user_has_this_pet.userid = new.id and uhtp.petid = p.id ;
	
	update user_has_this_pet
	set user_has_this_pet.status = 0
	where  new.status = 0 and user_has_this_pet.userid = new.id;
		
	update user_purchases
	set user_purchases.status = 0
	where  new.status = 0 and user_purchases.userid = new.id;
	
	return new;
end;
$$;
 <   DROP FUNCTION public.trig_func_clear_user_related_tables();
       public          postgres    false            �            1255    33339 #   trig_func_close_adoption_requests()    FUNCTION     �   CREATE FUNCTION public.trig_func_close_adoption_requests() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin  
	update adoption_requests
	set status = 0
	where petid = new.id and (new.status = 0 or new.isadopted = 1);
	
	return new;
end;
$$;
 :   DROP FUNCTION public.trig_func_close_adoption_requests();
       public          postgres    false            �            1255    33345 $   trig_func_remove_adoption_requests()    FUNCTION     >  CREATE FUNCTION public.trig_func_remove_adoption_requests() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    -- When a pet row is updated, set all adoption_requests statuses to 0
    UPDATE adoption_requests
    SET status = 0
    WHERE petid = OLD.petid;
	RAISE NOTICE 'ads removed';
    RETURN NEW;
END;
$$;
 ;   DROP FUNCTION public.trig_func_remove_adoption_requests();
       public          postgres    false            �            1259    33102    admin    TABLE     .   CREATE TABLE public.admin (
    id integer
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
       public         heap    postgres    false    216            �            1259    33019    items    TABLE     �   CREATE TABLE public.items (
    id integer DEFAULT nextval('public.item_id_seq'::regclass) NOT NULL,
    itemtypeid integer NOT NULL,
    itemtype character varying(255) NOT NULL,
    itemname character varying(255) NOT NULL,
    status integer
);
    DROP TABLE public.items;
       public         heap    postgres    false    218            �            1259    32988    pet_type_id_seq    SEQUENCE     x   CREATE SEQUENCE public.pet_type_id_seq
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
       public         heap    postgres    false    219            �            1259    32989 
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
       public         heap    postgres    false    219            �            1259    33044    user_has_this_pet    TABLE     x   CREATE TABLE public.user_has_this_pet (
    petid integer NOT NULL,
    status integer DEFAULT 1,
    userid integer
);
 %   DROP TABLE public.user_has_this_pet;
       public         heap    postgres    false            �            1259    24787    user_id_seq    SEQUENCE     t   CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false            �            1259    33061    user_purchases    TABLE     �   CREATE TABLE public.user_purchases (
    userid integer NOT NULL,
    itemid integer NOT NULL,
    purchasedate date,
    status integer DEFAULT 1
);
 "   DROP TABLE public.user_purchases;
       public         heap    postgres    false            �            1259    24788    users    TABLE     =  CREATE TABLE public.users (
    id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    username character varying(255),
    firstname character varying(255),
    surname character varying(255),
    address character varying(255),
    status integer DEFAULT 1,
    password character varying(255)
);
    DROP TABLE public.users;
       public         heap    postgres    false    214            P          0    33102    admin 
   TABLE DATA           #   COPY public.admin (id) FROM stdin;
    public          postgres    false    226   �H       Q          0    33123    adoption_requests 
   TABLE DATA           G   COPY public.adoption_requests (applicantid, petid, status) FROM stdin;
    public          postgres    false    227   I       G          0    32970 
   item_types 
   TABLE DATA           :   COPY public.item_types (id, itemtype, status) FROM stdin;
    public          postgres    false    217   JI       M          0    33019    items 
   TABLE DATA           K   COPY public.items (id, itemtypeid, itemtype, itemname, status) FROM stdin;
    public          postgres    false    223   �I       L          0    33006    pet 
   TABLE DATA           Q   COPY public.pet (id, pettypeid, pettype, petname, status, isadopted) FROM stdin;
    public          postgres    false    222   �I       K          0    32990 	   pet_types 
   TABLE DATA           8   COPY public.pet_types (id, pettype, status) FROM stdin;
    public          postgres    false    221   J       N          0    33044    user_has_this_pet 
   TABLE DATA           B   COPY public.user_has_this_pet (petid, status, userid) FROM stdin;
    public          postgres    false    224   [J       O          0    33061    user_purchases 
   TABLE DATA           N   COPY public.user_purchases (userid, itemid, purchasedate, status) FROM stdin;
    public          postgres    false    225   �J       E          0    24788    users 
   TABLE DATA           \   COPY public.users (id, username, firstname, surname, address, status, password) FROM stdin;
    public          postgres    false    215   �J       X           0    0    item_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.item_id_seq', 1, false);
          public          postgres    false    218            Y           0    0    item_type_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.item_type_id_seq', 5, true);
          public          postgres    false    216            Z           0    0 
   pet_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.pet_id_seq', 1, false);
          public          postgres    false    220            [           0    0    pet_type_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.pet_type_id_seq', 27, true);
          public          postgres    false    219            \           0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 31, true);
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
       public            postgres    false    221            �           2606    33050 -   user_has_this_pet user_has_this_pet_petid_key 
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
       public            postgres    false    215            �           2620    33348 $   users trig_clear_user_related_tables    TRIGGER     �   CREATE TRIGGER trig_clear_user_related_tables AFTER UPDATE ON public.users FOR EACH ROW WHEN ((old.id IS NOT NULL)) EXECUTE FUNCTION public.trig_func_clear_user_related_tables();
 =   DROP TRIGGER trig_clear_user_related_tables ON public.users;
       public          postgres    false    215    215    233            �           2620    33349 /   user_has_this_pet trig_remove_adoption_requests    TRIGGER     �   CREATE TRIGGER trig_remove_adoption_requests AFTER UPDATE ON public.user_has_this_pet FOR EACH ROW WHEN ((old.petid IS NOT NULL)) EXECUTE FUNCTION public.trig_func_remove_adoption_requests();
 H   DROP TRIGGER trig_remove_adoption_requests ON public.user_has_this_pet;
       public          postgres    false    224    224    232            �           2606    33105    admin admin_id_fkey    FK CONSTRAINT        ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_id_fkey FOREIGN KEY (id) REFERENCES public.users(id) ON DELETE CASCADE;
 =   ALTER TABLE ONLY public.admin DROP CONSTRAINT admin_id_fkey;
       public          postgres    false    215    3227    226            �           2606    33131 4   adoption_requests adoption_requests_applicantid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.adoption_requests
    ADD CONSTRAINT adoption_requests_applicantid_fkey FOREIGN KEY (applicantid) REFERENCES public.users(id) ON DELETE CASCADE;
 ^   ALTER TABLE ONLY public.adoption_requests DROP CONSTRAINT adoption_requests_applicantid_fkey;
       public          postgres    false    3227    227    215            �           2606    33136 .   adoption_requests adoption_requests_petid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.adoption_requests
    ADD CONSTRAINT adoption_requests_petid_fkey FOREIGN KEY (petid) REFERENCES public.pet(id) ON DELETE CASCADE;
 X   ALTER TABLE ONLY public.adoption_requests DROP CONSTRAINT adoption_requests_petid_fkey;
       public          postgres    false    227    3235    222            �           2606    33027    items items_itemtypeid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_itemtypeid_fkey FOREIGN KEY (itemtypeid) REFERENCES public.item_types(id) ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.items DROP CONSTRAINT items_itemtypeid_fkey;
       public          postgres    false    223    217    3229            �           2606    33014    pet pet_pettypeid_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_pettypeid_fkey FOREIGN KEY (pettypeid) REFERENCES public.pet_types(id);
 @   ALTER TABLE ONLY public.pet DROP CONSTRAINT pet_pettypeid_fkey;
       public          postgres    false    3233    221    222            �           2606    33056 .   user_has_this_pet user_has_this_pet_petid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_has_this_pet
    ADD CONSTRAINT user_has_this_pet_petid_fkey FOREIGN KEY (petid) REFERENCES public.pet(id);
 X   ALTER TABLE ONLY public.user_has_this_pet DROP CONSTRAINT user_has_this_pet_petid_fkey;
       public          postgres    false    3235    222    224            �           2606    33073 )   user_purchases user_purchases_itemid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_purchases
    ADD CONSTRAINT user_purchases_itemid_fkey FOREIGN KEY (itemid) REFERENCES public.items(id);
 S   ALTER TABLE ONLY public.user_purchases DROP CONSTRAINT user_purchases_itemid_fkey;
       public          postgres    false    3237    223    225            �           2606    33068 )   user_purchases user_purchases_userid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_purchases
    ADD CONSTRAINT user_purchases_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);
 S   ALTER TABLE ONLY public.user_purchases DROP CONSTRAINT user_purchases_userid_fkey;
       public          postgres    false    215    3227    225            P      x������ � �      Q      x�32�4�4�22�P��B���=... lt'      G   9   x�3�tN,Qp��O�4�2�t�O�q�9�2�R`<N���ϔ3(1))�1F��� ��      M      x������ � �      L   V   x�3�4�tN,�H-JL��L1D�0D�1�L0�,ф���B%��%`EF�H"F!#L!cL!t����(��x��9�P� ��:�      K   5   x�34�t�O�4�24�t�,J��9���2K@lN�����9$���� KM�      N      x�32�4�42�2�P1z\\\ D�      O      x������ � �      E   s  x�}Q�n�0</_�H����@R�E���C�J��l��FkS~��������_u�ȅ����gv=n�ad���>�{$�� �4�)�e��+Y�v%s҇"�|9x6�la�n�!-�3;�Lp3�va8k�/���o�������p/�+ʧ�I�0��Jq"6�֡��U]�q*�x1j�������7�eĹ԰۫�o�1�@$��v�!��!Y�:�3�'�g��5�����3L%$X*��3�f����MȽKRَBM�ۅf�����a-˦�-t�2����G�	-I���|��g�p��d��C*)��{���X����/�3ܬ~w�Ⱥ�%�~JH<��5�s�1c��PzqI'��>��
��Q�;�(��C��i����z��     