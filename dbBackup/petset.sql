PGDMP         5                {            PETSHOP    15.4    15.4 /    F           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            G           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            H           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            I           1262    24762    PETSHOP    DATABASE     }   CREATE DATABASE "PETSHOP" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_Turkey.1254';
    DROP DATABASE "PETSHOP";
                postgres    false            �            1255    33099 /   insert_into_user_has_this_pet(integer, integer)    FUNCTION       CREATE FUNCTION public.insert_into_user_has_this_pet(userid integer, petid integer) RETURNS text
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO user_has_this_pet(userid, petid,adoptiondate)
    VALUES (userid,petid,CURRENT_DATE);
	
	RETURN 'true';
END;
$$;
 S   DROP FUNCTION public.insert_into_user_has_this_pet(userid integer, petid integer);
       public          postgres    false            �            1259    33083    basket    TABLE     [   CREATE TABLE public.basket (
    userid integer,
    itemid integer,
    status integer
);
    DROP TABLE public.basket;
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
       public         heap    postgres    false    219            �            1259    33044    user_has_this_pet    TABLE     �   CREATE TABLE public.user_has_this_pet (
    petid integer NOT NULL,
    adoptiondate date,
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
       public         heap    postgres    false            �            1259    24788    users    TABLE       CREATE TABLE public.users (
    id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    username character varying(255),
    firstname character varying(255),
    surname character varying(255),
    address character varying(255),
    status integer DEFAULT 1
);
    DROP TABLE public.users;
       public         heap    postgres    false    214            C          0    33083    basket 
   TABLE DATA           8   COPY public.basket (userid, itemid, status) FROM stdin;
    public          postgres    false    226   �6       :          0    32970 
   item_types 
   TABLE DATA           :   COPY public.item_types (id, itemtype, status) FROM stdin;
    public          postgres    false    217   �6       @          0    33019    items 
   TABLE DATA           K   COPY public.items (id, itemtypeid, itemtype, itemname, status) FROM stdin;
    public          postgres    false    223   7       ?          0    33006    pet 
   TABLE DATA           Q   COPY public.pet (id, pettypeid, pettype, petname, status, isadopted) FROM stdin;
    public          postgres    false    222   57       >          0    32990 	   pet_types 
   TABLE DATA           8   COPY public.pet_types (id, pettype, status) FROM stdin;
    public          postgres    false    221   �7       A          0    33044    user_has_this_pet 
   TABLE DATA           P   COPY public.user_has_this_pet (petid, adoptiondate, status, userid) FROM stdin;
    public          postgres    false    224   �7       B          0    33061    user_purchases 
   TABLE DATA           N   COPY public.user_purchases (userid, itemid, purchasedate, status) FROM stdin;
    public          postgres    false    225   8       8          0    24788    users 
   TABLE DATA           R   COPY public.users (id, username, firstname, surname, address, status) FROM stdin;
    public          postgres    false    215   (8       J           0    0    item_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.item_id_seq', 1, false);
          public          postgres    false    218            K           0    0    item_type_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.item_type_id_seq', 1, false);
          public          postgres    false    216            L           0    0 
   pet_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.pet_id_seq', 1, false);
          public          postgres    false    220            M           0    0    pet_type_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.pet_type_id_seq', 14, true);
          public          postgres    false    219            N           0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 30, true);
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
       public            postgres    false    215            �           2606    33091    basket basket_itemid_fkey    FK CONSTRAINT     w   ALTER TABLE ONLY public.basket
    ADD CONSTRAINT basket_itemid_fkey FOREIGN KEY (itemid) REFERENCES public.items(id);
 C   ALTER TABLE ONLY public.basket DROP CONSTRAINT basket_itemid_fkey;
       public          postgres    false    223    226    3227            �           2606    33086    basket basket_userid_fkey    FK CONSTRAINT     w   ALTER TABLE ONLY public.basket
    ADD CONSTRAINT basket_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);
 C   ALTER TABLE ONLY public.basket DROP CONSTRAINT basket_userid_fkey;
       public          postgres    false    226    3217    215            �           2606    33027    items items_itemtypeid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_itemtypeid_fkey FOREIGN KEY (itemtypeid) REFERENCES public.item_types(id) ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.items DROP CONSTRAINT items_itemtypeid_fkey;
       public          postgres    false    223    3219    217            �           2606    33014    pet pet_pettypeid_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_pettypeid_fkey FOREIGN KEY (pettypeid) REFERENCES public.pet_types(id);
 @   ALTER TABLE ONLY public.pet DROP CONSTRAINT pet_pettypeid_fkey;
       public          postgres    false    222    221    3223            �           2606    33056 .   user_has_this_pet user_has_this_pet_petid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_has_this_pet
    ADD CONSTRAINT user_has_this_pet_petid_fkey FOREIGN KEY (petid) REFERENCES public.pet(id);
 X   ALTER TABLE ONLY public.user_has_this_pet DROP CONSTRAINT user_has_this_pet_petid_fkey;
       public          postgres    false    222    224    3225            �           2606    33073 )   user_purchases user_purchases_itemid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_purchases
    ADD CONSTRAINT user_purchases_itemid_fkey FOREIGN KEY (itemid) REFERENCES public.items(id);
 S   ALTER TABLE ONLY public.user_purchases DROP CONSTRAINT user_purchases_itemid_fkey;
       public          postgres    false    223    3227    225            �           2606    33068 )   user_purchases user_purchases_userid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_purchases
    ADD CONSTRAINT user_purchases_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);
 S   ALTER TABLE ONLY public.user_purchases DROP CONSTRAINT user_purchases_userid_fkey;
       public          postgres    false    225    215    3217            C      x������ � �      :      x������ � �      @      x������ � �      ?   e   x�3�4�tN,�H-J2�L�.���y`3��1L��&���(�2�H�?M!<�('�,oh@H�!!F�C��fgA�L�B�Ei��9)`�=... ��;�      >      x�3�tN,�4����� P?      A   ,   x�3�4202�54�56�4�42�2�14�2�2������ -e      B      x������ � �      8   h  x�}P�n�0<o���V-�yBE��*�*U��fV�MQ������ހ��P�������h-)GG$�X��H�Ĳ@ї�h�����C�S^�;[��[�bBk1�ri�	.�Nbd|�l@%��@�A�7T,P�`����L�~�C=[�VKp!�x5ju�����Ć�r!5j#x�9�"5l�3u�5LȲԉ\z�#�#k߬;o���qv�TA��2|z�h�XO�B�X6"7������v��ZSƨ�ܰ�U�y}�Y�~y����>I�X�
>=��v��@���Tƾ�_�����~S��nS�܃#�^,���ԓ�j��c�d���^@:L�˦NzO�$/����0I|��y���ʀ     