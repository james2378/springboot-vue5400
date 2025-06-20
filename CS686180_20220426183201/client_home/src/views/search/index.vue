<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="新闻资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="留言板"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_credit_value/list', 'get')"
				:list="result_student_credit_value_student_name"
				title="学生信誉值学生姓名"
				source_table="student_credit_value"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_user/list', 'get')"
				:list="result_student_user_student_name"
				title="学生用户学生姓名"
				source_table="student_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_user/list', 'get')"
				:list="result_student_user_student_gender"
				title="学生用户学生性别"
				source_table="student_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/volunteer_community/list', 'get')"
				:list="result_volunteer_community_community_name"
				title="志愿者社团社团名称"
				source_table="volunteer_community"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/volunteer_activities/list', 'get')"
				:list="result_volunteer_activities_activity_name"
				title="志愿者活动活动名称"
				source_table="volunteer_activities"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/group_record/list', 'get')"
				:list="result_group_record_community_name"
				title="加团记录社团名称"
				source_table="group_record"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/group_record/list', 'get')"
				:list="result_group_record_audit_status"
				title="加团记录审核状态"
				source_table="group_record"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registration_record/list', 'get')"
				:list="result_registration_record_activity_name"
				title="报名记录活动名称"
				source_table="registration_record"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registration_record/list', 'get')"
				:list="result_registration_record_student_name"
				title="报名记录学生姓名"
				source_table="registration_record"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/school_labor/list', 'get')"
				:list="result_school_labor_title_name"
				title="学校劳动标题名称"
				source_table="school_labor"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/participation_record/list', 'get')"
				:list="result_participation_record_title_name"
				title="参加记录标题名称"
				source_table="participation_record"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/participation_record/list', 'get')"
				:list="result_participation_record_student_name"
				title="参加记录学生姓名"
				source_table="participation_record"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_student_credit_value_student_name":[],
			"result_student_user_student_name":[],
			"result_student_user_student_gender":[],
			"result_volunteer_community_community_name":[],
			"result_volunteer_activities_activity_name":[],
			"result_group_record_community_name":[],
			"result_group_record_audit_status":[],
			"result_registration_record_activity_name":[],
			"result_registration_record_student_name":[],
			"result_school_labor_title_name":[],
			"result_participation_record_title_name":[],
			"result_participation_record_student_name":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取留言板
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取student_name
	 */
	get_student_credit_value_student_name(){
		this.$get("~/api/student_credit_value/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_credit_value_student_name = json.result.list;
			result_student_credit_value_student_name.map(o => o.title = o['student_name'])
	  			this.result_student_credit_value_student_name = result_student_credit_value_student_name
		 	}
		});
	},
	/**
	 * 获取student_name
	 */
	get_student_user_student_name(){
		this.$get("~/api/student_user/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_user_student_name = json.result.list;
			result_student_user_student_name.map(o => o.title = o['student_name'])
	  			this.result_student_user_student_name = result_student_user_student_name
		 	}
		});
	},
	/**
	 * 获取student_gender
	 */
	get_student_user_student_gender(){
		this.$get("~/api/student_user/get_list?like=0", { page: 1, size: 10, "student_gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_user_student_gender = json.result.list;
			result_student_user_student_gender.map(o => o.title = o['student_gender'])
	  			this.result_student_user_student_gender = result_student_user_student_gender
		 	}
		});
	},
	/**
	 * 获取community_name
	 */
	get_volunteer_community_community_name(){
		this.$get("~/api/volunteer_community/get_list?like=0", { page: 1, size: 10, "community_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_volunteer_community_community_name = json.result.list;
			result_volunteer_community_community_name.map(o => o.title = o['community_name'])
	  			this.result_volunteer_community_community_name = result_volunteer_community_community_name
		 	}
		});
	},
	/**
	 * 获取activity_name
	 */
	get_volunteer_activities_activity_name(){
		this.$get("~/api/volunteer_activities/get_list?like=0", { page: 1, size: 10, "activity_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_volunteer_activities_activity_name = json.result.list;
			result_volunteer_activities_activity_name.map(o => o.title = o['activity_name'])
	  			this.result_volunteer_activities_activity_name = result_volunteer_activities_activity_name
		 	}
		});
	},
	/**
	 * 获取community_name
	 */
	get_group_record_community_name(){
		this.$get("~/api/group_record/get_list?like=0", { page: 1, size: 10, "community_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_group_record_community_name = json.result.list;
			result_group_record_community_name.map(o => o.title = o['community_name'])
	  			this.result_group_record_community_name = result_group_record_community_name
		 	}
		});
	},
	/**
	 * 获取audit_status
	 */
	get_group_record_audit_status(){
		this.$get("~/api/group_record/get_list?like=0", { page: 1, size: 10, "audit_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_group_record_audit_status = json.result.list;
			result_group_record_audit_status.map(o => o.title = o['audit_status'])
	  			this.result_group_record_audit_status = result_group_record_audit_status
		 	}
		});
	},
	/**
	 * 获取activity_name
	 */
	get_registration_record_activity_name(){
		this.$get("~/api/registration_record/get_list?like=0", { page: 1, size: 10, "activity_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registration_record_activity_name = json.result.list;
			result_registration_record_activity_name.map(o => o.title = o['activity_name'])
	  			this.result_registration_record_activity_name = result_registration_record_activity_name
		 	}
		});
	},
	/**
	 * 获取student_name
	 */
	get_registration_record_student_name(){
		this.$get("~/api/registration_record/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registration_record_student_name = json.result.list;
			result_registration_record_student_name.map(o => o.title = o['student_name'])
	  			this.result_registration_record_student_name = result_registration_record_student_name
		 	}
		});
	},
	/**
	 * 获取title_name
	 */
	get_school_labor_title_name(){
		this.$get("~/api/school_labor/get_list?like=0", { page: 1, size: 10, "title_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_school_labor_title_name = json.result.list;
			result_school_labor_title_name.map(o => o.title = o['title_name'])
	  			this.result_school_labor_title_name = result_school_labor_title_name
		 	}
		});
	},
	/**
	 * 获取title_name
	 */
	get_participation_record_title_name(){
		this.$get("~/api/participation_record/get_list?like=0", { page: 1, size: 10, "title_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_participation_record_title_name = json.result.list;
			result_participation_record_title_name.map(o => o.title = o['title_name'])
	  			this.result_participation_record_title_name = result_participation_record_title_name
		 	}
		});
	},
	/**
	 * 获取student_name
	 */
	get_participation_record_student_name(){
		this.$get("~/api/participation_record/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_participation_record_student_name = json.result.list;
			result_participation_record_student_name.map(o => o.title = o['student_name'])
	  			this.result_participation_record_student_name = result_participation_record_student_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_student_credit_value_student_name();
		this.get_student_user_student_name();
		this.get_student_user_student_gender();
		this.get_volunteer_community_community_name();
		this.get_volunteer_activities_activity_name();
		this.get_group_record_community_name();
		this.get_group_record_audit_status();
		this.get_registration_record_activity_name();
		this.get_registration_record_student_name();
		this.get_school_labor_title_name();
		this.get_participation_record_title_name();
		this.get_participation_record_student_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_student_credit_value_student_name();
	  this.get_student_user_student_name();
	  this.get_student_user_student_gender();
	  this.get_volunteer_community_community_name();
	  this.get_volunteer_activities_activity_name();
	  this.get_group_record_community_name();
	  this.get_group_record_audit_status();
	  this.get_registration_record_activity_name();
	  this.get_registration_record_student_name();
	  this.get_school_labor_title_name();
	  this.get_participation_record_title_name();
	  this.get_participation_record_student_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
