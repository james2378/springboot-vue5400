<template>
	<div class="diy_edit page_registration_record" id="registration_record_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','activity_name') || $check_field('add','activity_name') || $check_field('get','activity_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>活动名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_activity_name" v-model="form['activity_name']" placeholder="请输入活动名称" v-if="(form['activity_name'] && $check_field('set','activity_name')) || (!form['activity_name'] && $check_field('add','activity_name'))"  :disabled="disabledObj['activity_name_isDisabled']"/>
							<span v-else-if="$check_field('get','activity_name')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','activity_time') || $check_field('add','activity_time') || $check_field('get','activity_time')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>活动时间:
							</span>
						</div>
						<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['activity_time_isDisabled']" id="form_activity_time" v-model="form['activity_time']" placeholder="请输入活动时间" v-if="(form['activity_time'] && $check_field('set','activity_time')) || (!form['activity_time'] && $check_field('add','activity_time'))" />
							<span v-else-if="$check_field('get','activity_time')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_information') || $check_field('add','student_information') || $check_field('get','student_information')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>学生信息:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_student_information" :disabled="disabledObj['student_information_isDisabled']" v-model="form['student_information']" v-if="(form['student_information'] && $check_field('set','student_information')) || (!form['student_information'] && $check_field('add','student_information'))" >
								<option v-for="o in list_user_student_information" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','student_information')">{{ form['student_information'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_name') || $check_field('add','student_name') || $check_field('get','student_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>学生姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_student_name" v-model="form['student_name']" placeholder="请输入学生姓名" v-if="(form['student_name'] && $check_field('set','student_name')) || (!form['student_name'] && $check_field('add','student_name'))"  :disabled="disabledObj['student_name_isDisabled']"/>
							<span v-else-if="$check_field('get','student_name')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_number') || $check_field('add','student_number') || $check_field('get','student_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>学生学号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_student_number" v-model="form['student_number']" placeholder="请输入学生学号" v-if="(form['student_number'] && $check_field('set','student_number')) || (!form['student_number'] && $check_field('add','student_number'))"  :disabled="disabledObj['student_number_isDisabled']"/>
							<span v-else-if="$check_field('get','student_number')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contact_number') || $check_field('add','contact_number') || $check_field('get','contact_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>联系电话:
							</span>
						</div>
						<!-- 手机 -->
						<div class="diy_field diy_phone">
							<input type="tel" id="form_contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" v-if="(form['contact_number'] && $check_field('set','contact_number')) || (!form['contact_number'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']" />
							<span v-else-if="$check_field('get','contact_number')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','registration_status') || $check_field('add','registration_status') || $check_field('get','registration_status')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>报名状态:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_registration_status" v-model="form['registration_status']" v-if="(form['registration_status'] && $check_field('set','registration_status')) || (!form['registration_status'] && $check_field('add','registration_status'))" >
								<option v-for="o in list_registration_status" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','registration_status')">{{ form['registration_status'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','participation_status') || $check_field('add','participation_status') || $check_field('get','participation_status')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>参加状态:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_participation_status" v-model="form['participation_status']" v-if="(form['participation_status'] && $check_field('set','participation_status')) || (!form['participation_status'] && $check_field('add','participation_status'))" >
								<option v-for="o in list_participation_status" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','participation_status')">{{ form['participation_status'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','registration_reply') || $check_field('add','registration_reply') || $check_field('get','registration_reply')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>报名回复:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_registration_reply" v-model="form['registration_reply']" v-if="(form['registration_reply'] && $check_field('set','registration_reply')) || (!form['registration_reply'] && $check_field('add','registration_reply'))" :disabled="disabledObj['registration_reply_isDisabled']" />
							<span v-else-if="$check_field('get','registration_reply')">{{ form['student_name'] }}</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/registration_record/get_obj?",
				url_add: "~/api/registration_record/add?",
				url_set: "~/api/registration_record/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"activity_name": "",
					"activity_time": "",
					"student_information": 0,
					"student_name": "",
					"student_number": "",
					"contact_number": "",
					"registration_status": "",
					"participation_status": "",
					"registration_reply": "",
					"registration_record_id": 0,
				},

				obj: {
					"activity_name":'', // 活动名称
					"activity_time": new Date().getTime(),
					"student_information": 0, // 学生信息
					"student_name":'', // 学生姓名
					"student_number":'', // 学生学号
					"contact_number":'', // 联系电话
					"registration_status":'', // 报名状态
					"participation_status":'', // 参加状态
					"registration_reply":'', // 报名回复
					"registration_record_id": 0,
				},

				// 表单字段
				form: {
					"activity_name":'', // 活动名称
					"activity_time": new Date().getTime(),
					"student_information": 0, // 学生信息
					"student_name":'', // 学生姓名
					"student_number":'', // 学生学号
					"contact_number":'', // 联系电话
					"registration_status":'', // 报名状态
					"participation_status":'', // 参加状态
					"registration_reply":'', // 报名回复
					"registration_record_id": 0,
				},
				disabledObj:{
					"activity_name_isDisabled": false,
					"activity_time_isDisabled": false,
					"student_information_isDisabled": false,
					"student_name_isDisabled": false,
					"student_number_isDisabled": false,
					"contact_number_isDisabled": false,
					"registration_status_isDisabled": false,
					"participation_status_isDisabled": false,
					"registration_reply_isDisabled": false,
				},
				// 用户列表
				list_user_student_information: [],
				//报名状态选项列表
				list_registration_status: ['未通过','已通过'],
				//参加状态选项列表
				list_participation_status: ['未参加','已参加'],

				// ID字段
				field: "registration_record_id",
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
			async get_user_session_student_information(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["student_information"] = user_id
								_this.disabledObj['student_information' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "student_information") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/registration_record/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
            }
          }
        }
        if (JSON.stringify(this.form["activity_time"]).indexOf("-")===-1) {
          this.form["activity_time"] = this.$toTime(parseInt(this.form["activity_time"]), "yyyy-MM-dd")
        }

        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_student_information();
			this.get_list_user_student_information();
		},
	}
</script>

<style>
</style>
