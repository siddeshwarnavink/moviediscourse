<?php
 
namespace App\Models;
 
use Illuminate\Database\Eloquent\Model;
use App\Models\Comment;
 
class Movie extends Model
{
    protected $table = 'movies';


    public function comments()
    {
        return $this->hasMany(Comment::class, 'movie'); 
    }
}

