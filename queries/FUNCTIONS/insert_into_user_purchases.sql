CREATE OR REPLACE FUNCTION insert_into_user_purchases(userID INT, itemID INT)
RETURNS TEXT AS
$$
BEGIN
    INSERT INTO user_purchases(userid,itemid,purchasedate) VALUES (userID, itemID,DATE);
	RETURN 'done'
END;
$$
LANGUAGE PLPGSQL;