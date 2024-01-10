CREATE OR REPLACE FUNCTION trig_func_remove_adoption_requests()
RETURNS TRIGGER AS $$
BEGIN
    -- When a pet row is updated, set all adoption_requests statuses to 0
    UPDATE adoption_requests
    SET status = 0
    WHERE petid = OLD.petid;
	RAISE NOTICE 'ads removed';
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trig_remove_adoption_requests
AFTER UPDATE ON user_has_this_pet
FOR EACH ROW
EXECUTE FUNCTION trig_func_remove_adoption_requests();