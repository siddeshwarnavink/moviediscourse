<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('comments', function (Blueprint $table) {
            $table->id();
            $table->text('commentText');
            $table->unsignedBigInteger('parent')->nullable();
            $table->unsignedBigInteger('creator');
            $table->unsignedBigInteger('movie');
            $table->timestamps();

            $table->foreign('parent')
                ->references('id')
                ->on('comments')
                ->onDelete('cascade');

            $table->foreign('creator')
                ->references('id')
                ->on('users')
                ->onDelete('cascade'); 

            $table->foreign('movie')
                ->references('id')
                ->on('movies')
                ->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('comments');
    }
};
