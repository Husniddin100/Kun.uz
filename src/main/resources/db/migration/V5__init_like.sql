/*CREATE
OR REPLACE FUNCTION article_like_trigger_function()
RETURNS TRIGGER
LANGUAGE PLPGSQL
AS $$
BEGIN
--NEW OLD
    IF
TG_OP = 'INSERT' then
		IF NEW.status = 'LIKE' then
update article
set like_count = like_count + 1
where id = NEW.article_id;
elseif
NEW.STATUS = 'DISLIKE' then
update article
set dislike_count = dislike_count + 1
where id = NEW.article_id;
end if;
	ELSEIF
TG_OP = 'UPDATE' then
		 IF NEW.status = 'LIKE' and OLD.status = 'DISLIKE' then
update article
set like_count    = like_count + 1,
    dislike_count = dislike_count - 1
where id = NEW.article_id;
elseif
NEW.STATUS = 'DISLIKE' and OLD.status = 'LIKE' then
update article
set like_count    = like_count - 1,
    dislike_count = dislike_count + 1
where id = NEW.article_id;
end if;
	ELSEIF
TG_OP = 'DELETE' then
		 IF OLD.status = 'LIKE' then
update article
set like_count = like_count - 1
where id = OLD.article_id;
elseif
OLD.STATUS = 'DISLIKE' then
update article
set dislike_count = dislike_count - 1
where id = OLD.article_id;
end if;
return OLD;
end if;
return NEW;
END; $$

   CREATE
or REPLACE TRIGGER article_like_trigger
	BEFORE INSERT OR
UPDATE OR
DELETE
ON article_like
    FOR EACH ROW
    EXECUTE PROCEDURE article_like_trigger_function();*/