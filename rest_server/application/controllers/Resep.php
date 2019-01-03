<?php 
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;

class Resep extends REST_Controller {
//$this->response(array("status"=>"success","result" => $get_pembeli));
//$this->response(array("status"=>"success"));
	function resep_get($kata=""){
		if($kata!=""){
			$this->db->or_like("judul",$kata);
		}
		$get_pembeli = $this->db->get("resep")->result();
		$this->response(array("status"=>"success","result" => $get_pembeli));
	}
	function resepKategori_get($kategori=""){
		if($kategori!=""){
			$this->db->where("fk_kategori",$kategori);
		}
		$get_pembeli = $this->db->get("resep")->result();
		$this->response(array("status"=>"success","result" => $get_pembeli));
	}
	function review_get($id_resep){
		// $id_resep = $this->get('id_resep');
		if ($id_resep != "") {
			$get_pembeli = $this->db->where('id_resep',$id_resep)->get("review")->result();
			$this->response(array("status"=>"success","result" => $get_pembeli));
		}else{
			$this->response(array("status"=>"failed","message" => "Id_resep harus di isi"));
		}
	}
	function review_post() {

		$data_review = array(
			'id_resep' => $this->post('id_resep'),
			'konten' => $this->post('konten'),
			'photo_id' => $this->post('photo_id')
		);

			$this->insertReview($data_review);
	}
	function insertReview($data_review){
//function upload image
		$uploaddir = str_replace("application/", "", APPPATH).'upload/';
		if(!file_exists($uploaddir) && !is_dir($uploaddir)) {
			echo mkdir($uploaddir, 0750, true);
		}
		if (!empty($_FILES)){
			$path = $_FILES['photo_id']['name'];
			$ext = pathinfo($path, PATHINFO_EXTENSION);
			$user_img = $data_review['id_resep'].rand(100,999). '.' . "png";
			$uploadfile = $uploaddir . $user_img;
			$data_review['photo_id'] = "upload/".$user_img;
		}else{
			$data_review['photo_id']="";
		}

		if (empty($data_review['id_resep'])){
			$this->response(array('status' => "failed", "message"=>"id_lowongan harus
				diisi"));
		}else{
				$insert= $this->db->insert('review',$data_review);
				if (!empty($_FILES)){
					if ($_FILES["photo_id"]["name"]) {	
						if
							(move_uploaded_file($_FILES["photo_id"]["tmp_name"],$uploadfile))
						{
							$insert_image = "success";

						} else{
							$insert_image = "failed";

						}
					}else{
						$insert_image = "Image Tidak ada Masukan";
					}
					$data_review['photo_id'] = "upload/".$user_img;
				}else{

					$data_review['photo_id'] = "";

				}
				if ($insert){
					$data_review['id'] = $this->db->insert_id();
					$this->response(array('status'=>'success','result' =>

						array($data_review),"message"=>$insert));

				}
			
		}
	}	
}