alter table ACT_RU_JOB add CORRELATION_ID_ nvarchar(255);
alter table ACT_RU_TIMER_JOB add CORRELATION_ID_ nvarchar(255);
alter table ACT_RU_SUSPENDED_JOB add CORRELATION_ID_ nvarchar(255);
alter table ACT_RU_DEADLETTER_JOB add CORRELATION_ID_ nvarchar(255);
alter table ACT_RU_EXTERNAL_JOB add CORRELATION_ID_ nvarchar(255);

create index ACT_IDX_JOB_CORRELATION_ID on ACT_RU_JOB(CORRELATION_ID_);
create index ACT_IDX_TIMER_JOB_CORRELATION_ID on ACT_RU_TIMER_JOB(CORRELATION_ID_);
create index ACT_IDX_SUSPENDED_JOB_CORRELATION_ID on ACT_RU_SUSPENDED_JOB(CORRELATION_ID_);
create index ACT_IDX_DEADLETTER_JOB_CORRELATION_ID on ACT_RU_DEADLETTER_JOB(CORRELATION_ID_);
create index ACT_IDX_EXTERNAL_JOB_CORRELATION_ID on ACT_RU_EXTERNAL_JOB(CORRELATION_ID_);

update ACT_GE_PROPERTY set VALUE_ = '6.5.1.4' where NAME_ = 'job.schema.version';