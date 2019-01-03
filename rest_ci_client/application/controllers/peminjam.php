<?php 
 
defined('BASEPATH') OR exit('No direct script access allowed'); 
 
require APPPATH . '/libraries/REST_Controller.php'; 
use Restserver\Libraries\REST_Controller; 
 
class peminjam extends REST_Controller { 
    function __construct($config = 'rest') {         
    	parent::__construct($config);         
    	$this->load->database();     } 
 
    //Menampilkan data kontak     
    function index_get() {         
    	$id_peminjam = $this->get('id_peminjam');         
    	if ($id_peminjam == '') {             
    		$perpus_pinjam = $this->db->get('peminjam')->result();         
    	} else {             
    		$this->db->where('id_peminjam', $id_peminjam);             
    		$perpus_pinjam = $this->db->get('peminjam')->result();         
    	}         
    	$this->response($perpus_pinjam, 200);     
    } 
 
 
    //menambah peminjam baru
    function index_post() {         
    	$data = array(                     
    		'id_peminjam'           => $this->post('id_peminjam'),                     
    		'nama'          => $this->post('nama'),                     
    		'alamat'    => $this->post('alamat'),
    		'tlp'		=> $this->post('tlp'));         
    	$insert = $this->db->insert('peminjam', $data);         
    	if ($insert) {             
    		$this->response($data, 200);         
    	} else {             
    		$this->response(array('status' => 'fail', 502));         
    	}     
    }
} 
?> 