<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','community_name') || $check_field('add','community_name') || $check_field('set','community_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="社团名称" prop="community_name">
					<el-input id="community_name" v-model="form['community_name']" placeholder="请输入社团名称"
							  v-if="user_group === '管理员' || (form['volunteer_community_id'] && $check_field('set','community_name')) || (!form['volunteer_community_id'] && $check_field('add','community_name'))" :disabled="disabledObj['community_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','community_name')">{{form['community_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','group_establishment_time') || $check_field('add','group_establishment_time') || $check_field('set','group_establishment_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="建团时间" prop="group_establishment_time">
					<el-date-picker :disabled="disabledObj['group_establishment_time_isDisabled']" v-if="user_group === '管理员' || (form['volunteer_community_id'] && $check_field('set','group_establishment_time')) || (!form['volunteer_community_id'] && $check_field('add','group_establishment_time'))" id="group_establishment_time"
						v-model="form['group_establishment_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','group_establishment_time')">{{form['group_establishment_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_associations') || $check_field('add','number_of_associations') || $check_field('set','number_of_associations')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="社团人数" prop="number_of_associations">
					<el-input id="number_of_associations" v-model="form['number_of_associations']" placeholder="请输入社团人数"
							  v-if="user_group === '管理员' || (form['volunteer_community_id'] && $check_field('set','number_of_associations')) || (!form['volunteer_community_id'] && $check_field('add','number_of_associations'))" :disabled="disabledObj['number_of_associations_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_associations')">{{form['number_of_associations']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover_photo_') || $check_field('add','cover_photo_') || $check_field('set','cover_photo_')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面图片" prop="cover_photo_">
					<el-upload :disabled="disabledObj['cover_photo__isDisabled']" id="cover_photo_" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_photo_"
						:show-file-list="false" v-if="user_group === '管理员' || (form['volunteer_community_id'] && $check_field('set','cover_photo_')) || (!form['volunteer_community_id'] && $check_field('add','cover_photo_'))">
						<img v-if="form['cover_photo_']" :src="$fullUrl(form['cover_photo_'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_photo_')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_photo_'])" :preview-src-list="[$fullUrl(form['cover_photo_'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','community_profile') || $check_field('add','community_profile') || $check_field('set','community_profile')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="社团简介" prop="community_profile">
					<quill-editor v-model.number="form['community_profile']"
						v-if="user_group === '管理员' || (form['volunteer_community_id'] && $check_field('set','community_profile')) || (!form['volunteer_community_id'] && $check_field('add','community_profile')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','community_profile')" v-html="form['community_profile']"></div>
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
				field: "volunteer_community_id",
				url_add: "~/api/volunteer_community/add?",
				url_set: "~/api/volunteer_community/set?",
				url_get_obj: "~/api/volunteer_community/get_obj?",
				url_upload: "~/api/volunteer_community/upload?",

				query: {
					"volunteer_community_id": 0,
				},

				form: {
					"community_name":'', // 社团名称
					"group_establishment_time":'', // 建团时间
					"number_of_associations":'', // 社团人数
					"cover_photo_":'', // 封面图片
					"community_profile":'', // 社团简介
					"volunteer_community_id": 0, // ID

				},
				disabledObj:{
					"community_name_isDisabled": false,
					"group_establishment_time_isDisabled": false,
					"number_of_associations_isDisabled": false,
					"cover_photo__isDisabled": false,
					"community_profile_isDisabled": false,
				},
			}
		},
		methods: {
			/**
			 * 上传封面图片
			 * @param {Object} param图片参数
			 */
			upload_cover_photo_(param){
				this.uploadFile(param.file, "cover_photo_");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
        if (this.form["group_establishment_time"].indexOf("-")===-1){
          this.form["group_establishment_time"] = this.$toTime(parseInt(this.form["group_establishment_time"]),"yyyy-MM-dd")
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
        if(this.form["group_establishment_time"]=="0000-00-00"){
          this.form["group_establishment_time"] = null;
        }
				if(parseInt(this.form["group_establishment_time"]) > 9999){
					this.form["group_establishment_time"] = this.$toTime(parseInt(this.form["group_establishment_time"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/volunteer_community/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/volunteer_community/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/volunteer_community/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/volunteer_community/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/volunteer_community/view','get');
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
