
DROP FUNCTION insert_into_user_has_this_pet(userid INT, petid INT);

CREATE OR REPLACE FUNCTION insert_into_user_has_this_pet(userid INT, petid INT) RETURNS text AS $$
BEGIN
    INSERT INTO user_has_this_pet(userid, petid,adoptiondate)
    VALUES (userid,petid,CURRENT_DATE);
	
	RETURN 'true';
END;
$$ LANGUAGE plpgsql;

