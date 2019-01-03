<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Review extends CI_Controller {

	public function index($id = '')
	{
		if ($id == '') {
			redirect('Welcome');
		}
		$data['id']=$id;
		$this->load->view('review',$data);
	}
}
