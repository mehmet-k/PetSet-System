DROP TRIGGER trig_clear_user_related_tables ON users

create or replace function trig_func_clear_user_related_tables()
returns trigger as $$
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
$$ language 'plpgsql';

create trigger trig_clear_user_related_tables
after update 
on users
for each row 
when (old.id is not null)
execute procedure trig_func_clear_user_related_tables();