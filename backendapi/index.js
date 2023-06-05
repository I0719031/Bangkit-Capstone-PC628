const express = require('express');
const app = express();
const port=8080;

app.use(express.json())

app.get('/', (req, res) =>{
    res.json("Hello Leckers app")
});

app.get('/tshirt/:id', async (req, res) => {

    const {id} = req.params;

    res.send({
        tshirt:`your tshirt ID ${id}`,
    });
});


app.get('/daging', async (req, res)=> {
    try{
        const data = await prisma.daging.findMany()
        res.json({success: true, data});
        return;
    }catch(error){
        res.json({success: false, message: error});
        return;
    }
})

app.get(`/daging/:id`, async (req, res) => {
    const { id } = req.params
  try {
    const getdaging = await prisma.daging.findUnique({
      where: { daging_id: Number(id) },
    })
  
    if(getdaging == null) {
      res.status(400).json('Kosong')
      return;
    }
      res.json(getdaging)
      return;
      
  } catch (error) {
    res.json({success: false, message: error});
     return;
  }
  }) 

app.listen(
    port,
    () => console.log(`it's alive on http://localhost:${port}`)
)