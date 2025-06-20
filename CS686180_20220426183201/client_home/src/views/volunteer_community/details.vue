<template>
	<div class="diy_details page_volunteer_community" id="volunteer_community_details">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div class='col'>
						<div class='card_volunteer_community'>
							<div_volunteer_community :obj="obj"></div_volunteer_community>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import div_volunteer_community from "@/components/diy/div_volunteer_community.vue";
	import mixin from "@/mixins/page.js";
	export default {
		mixins:[mixin],
		components:{
			div_volunteer_community
		},
		data(){
			return{
				url_get_obj: "~/api/volunteer_community/get_obj?",

				field: "volunteer_community_id",

				obj: {
				},

				query: {
					"community_name": "",
					"group_establishment_time": "",
					"number_of_associations": "",
					"cover_photo_": "",
					"community_profile": "",
					"volunteer_community_id":0,
				},
				// 点赞
				praise: 0,
				// 点赞状态
				state_praise: false,
				
			}
		},
		methods:{
			/**
			 * 添加访问量
			 */
			add_hits(obj) {
				obj["hits"] = obj["hits"] + 1;
				var hits = obj["hits"];
				this.$post('~/api/volunteer_community/set?volunteer_community_id=' + obj["volunteer_community_id"], {
					hits
				}, (res) => {
					if(res.result){
						console.log("添加访问量状态：" ,res.result);
						var body = {
							source_table: "volunteer_community",
							source_field: "volunteer_community_id",
							source_id: this.obj.volunteer_community_id,
							user_id: this.$store.state.user.user_id,
						};
						this.$post("~/api/hits/add?", body, (res) => {
							console.log(res);
						});
					}
					else if(res.error){
						console.error(res.error);
					}
				});
			},
			get_obj_after(json) {
				// 判断是否获取到数据
				if (json && json.result && json.result.obj) {
					console.log(json.result.obj)
					var obj = json.result.obj;
					this.obj = obj
					// 增加点击数
					this.add_hits(obj);
				}
			},
		},
		
		mounted() {
			
		},
		
	}
</script>

<style>
.qecode {
  display: flex;
  align-content: center;
  width: 270px;
  background: white;
  position: absolute;
  left: 20%;
  top: 20px;
  z-index: 999;
}
.qecodeImg {
  display: flex;
  flex-direction: column;
  align-content: center;
  text-align: center;
  padding-top: 10px;
  margin: 0 auto;
}
.btn_share{
	display: inline-block;
	position: relative;
	top: 15px;
	background: rgb(240, 238, 238);
	border: 1px solid #ccc;
	height: 38px;
	margin-left: 10px;
	padding: 3px 10px;
	cursor: pointer;
	overflow: hidden;
	border-radius: 5px;
}
.btn_share_icon{
	width: 30px;
	height: 28px;
	float: left;
	margin-right: 10px;
}
.share_box{
	display: none;
	float: left;
}
.share_box img{
	width: 30px;
	height: 28px;
	margin-right: 10px;
}
.btn_share:hover .share_box{
	display: block;
}
</style>
