<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','community_name') || $check_field('add','community_name') || $check_field('set','community_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="社团名称" prop="community_name">
					<el-input id="community_name" v-model="form['community_name']" placeholder="请输入社团名称"
							  v-if="user_group === '管理员' || (form['group_record_id'] && $check_field('set','community_name')) || (!form['group_record_id'] && $check_field('add','community_name'))" :disabled="disabledObj['community_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','community_name')">{{form['community_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_information') || $check_field('add','student_information') || $check_field('set','student_information')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生信息" prop="student_information">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_information(form['student_information']) }}
							<!--<el-input id="business_name" v-model="form['student_information']" placeholder="请输入学生信息"-->
							<!--v-if="user_group === '管理员' || (form['group_record_id'] && $check_field('set','student_information')) || (!form['group_record_id'] && $check_field('add','student_information'))" :disabled="disabledObj['student_information_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_information')">{{form['student_information']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['group_record_id'] && $check_field('set','student_information')) || (!form['group_record_id'] && $check_field('add','student_information'))" id="student_information" v-model="form['student_information']" :disabled="disabledObj['student_information_isDisabled']">
								<el-option v-for="o in list_user_student_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','student_information')" id="student_information" v-model="form['student_information']" :disabled="true">
								<el-option v-for="o in list_user_student_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student_information" v-model="form['student_information']" :disabled="disabledObj['student_information_isDisabled']">
							<el-option v-for="o in list_user_student_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生姓名" prop="student_name">
					<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="user_group === '管理员' || (form['group_record_id'] && $check_field('set','student_name')) || (!form['group_record_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生学号" prop="student_number">
					<el-input id="student_number" v-model="form['student_number']" placeholder="请输入学生学号"
							  v-if="user_group === '管理员' || (form['group_record_id'] && $check_field('set','student_number')) || (!form['group_record_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" type="tel"
						v-if="user_group === '管理员' || (form['group_record_id'] && $check_field('set','contact_number')) || (!form['group_record_id'] && $check_field('add','contact_number'))">
					</el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','audit_status') || $check_field('add','audit_status') || $check_field('set','audit_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核状态" prop="audit_status">
					<el-select id="audit_status" v-model="form['audit_status']"
						v-if="user_group === '管理员' || (form['group_record_id'] && $check_field('set','audit_status')) || (!form['group_record_id'] && $check_field('add','audit_status'))">
						<el-option v-for="o in list_audit_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','audit_status')">{{form['audit_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reasons_for_adding_group') || $check_field('add','reasons_for_adding_group') || $check_field('set','reasons_for_adding_group')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="加团理由" prop="reasons_for_adding_group">
					<el-input type="textarea" id="reasons_for_adding_group" v-model="form['reasons_for_adding_group']" placeholder="请输入加团理由"
						v-if="user_group === '管理员' || (form['group_record_id'] && $check_field('set','reasons_for_adding_group')) || (!form['group_record_id'] && $check_field('add','reasons_for_adding_group'))" :disabled="disabledObj['reasons_for_adding_group_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reasons_for_adding_group')">{{form['reasons_for_adding_group']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','review_reply') || $check_field('add','review_reply') || $check_field('set','review_reply')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核回复" prop="review_reply">
					<el-input type="textarea" id="review_reply" v-model="form['review_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['group_record_id'] && $check_field('set','review_reply')) || (!form['group_record_id'] && $check_field('add','review_reply'))" :disabled="disabledObj['review_reply_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','review_reply')">{{form['review_reply']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "group_record_id",
				url_add: "~/api/group_record/add?",
				url_set: "~/api/group_record/set?",
				url_get_obj: "~/api/group_record/get_obj?",
				url_upload: "~/api/group_record/upload?",

				query: {
					"group_record_id": 0,
				},

				form: {
					"community_name":'', // 社团名称
					"student_information": 0, // 学生信息
					"student_name":'', // 学生姓名
					"student_number":'', // 学生学号
					"contact_number":'', // 联系电话
					"audit_status":'', // 审核状态
					"reasons_for_adding_group":'', // 加团理由
					"review_reply":'', // 审核回复
					"group_record_id": 0, // ID

				},
				disabledObj:{
					"community_name_isDisabled": false,
					"student_information_isDisabled": false,
					"student_name_isDisabled": false,
					"student_number_isDisabled": false,
					"contact_number_isDisabled": false,
					"audit_status_isDisabled": false,
					"reasons_for_adding_group_isDisabled": false,
					"review_reply_isDisabled": false,
				},
				// 用户列表
				list_user_student_information: [],
				// 用户组
				group_user_student_information: "",
				//审核状态选项列表
				list_audit_status: ['未通过','已通过'],
			}
		},
		methods: {
			/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_student_information() {
                // if(this.user_group !== "管理员" && this.form["student_information"] === 0) {
                //     this.form["student_information"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_student_information = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取学生用户用户组
			 */
			async get_group_user_student_information() {
				this.form["student_information"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					this.group_user_student_information = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_student_information(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_student_information.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                for (var j = 0; j < arrForm.length; j++) {
                  if (arr[i] === arrForm[j]) {
                    if (arr[i] !== "student_information") {
                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                      break;
                    } else {
                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                    }
                  }
                }
              }
						}
					}
				});
			},
			get_user_student_information(id){
				var obj = this.list_user_student_information.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学生信息":
							if(param["student_information"] > 0){
								param["student_information"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/group_record/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/group_record/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/group_record/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/group_record/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/group_record/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_student_information();
			this.get_group_user_student_information();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
