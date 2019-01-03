<?php $this->load->view('header') ?>

    <main role="main" class="container">
      <div class="jumbotron">
        <h1>Navbar example</h1>
        <p class="lead">This example is a quick exercise to illustrate how the top-aligned navbar works. As you scroll, this navbar remains in its original position and moves with the rest of the page.</p>
        <a class="btn btn-lg btn-primary" href="../../components/navbar/" role="button">View navbar docs &raquo;</a>
      </div>
      <div class="row">
      	<div class="col-md-12">
      		<table class="table table-bordered table-hover">
      			<thead>
      				<tr>
      					<th>No</th>
      					<th>Konten</th>
      					<th>Image</th>
      				</tr>
      			</thead>
      			<tbody>
      				<?php foreach ($this->db->where('id_resep',$id)->get('review')->result() as $key => $value): ?>
      					<tr>
      						<td><?php echo ++$key; ?></td>
      						<td><?php echo $value->konten ?></td>
      						<td>
                      <img src="<?php echo 'http://localhost/rest_server/application/'.$value->photo_id ?>" width="100px" alt="">      
                  </td>
							
      					</tr>
      				<?php endforeach ?>
      			</tbody>
      		</table>
      	</div>
      </div>
    </main>
<?php $this->load->view('footer') ?>